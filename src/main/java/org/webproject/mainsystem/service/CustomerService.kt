package org.webproject.mainsystem.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.webproject.mainsystem.model.dao.ReportDao
import org.webproject.mainsystem.model.dao.RequestDao
import org.webproject.mainsystem.model.dto.request.*
import org.webproject.mainsystem.model.dto.response.RequestListResponseModel
import org.webproject.mainsystem.model.enumData.RequestStatus
import org.webproject.mainsystem.repository.*
import org.webproject.responsewrapper.custom.exception.DefaultSupportedException
import org.webproject.responsewrapper.custom.exception.SupportedMissMatchException
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Service
class CustomerService @Autowired constructor(
    private val customerRepo: CustomerRepository,
    private val requestRepo: RequestRepository,
    private val carRepo: SupportedCarRepository,
    private val skillRepo: SkillRepository,
    private val repairManRepo: RepairManRepository,
    private val reportRepo: ReportRepository
) {

    fun addRepairRequest(customer: UUID, requestModel: RequestRepairRequestModel) {
        val customerDb= customerRepo.findById(customer).orElseThrow { throw SupportedMissMatchException("User Not Found",404) }

        if(LocalDateTime.ofEpochSecond(requestModel.to_Date,0, ZoneOffset.UTC).isBefore(LocalDateTime.now())) throw SupportedMissMatchException("Your Time is passed",405)

        val requestDao = requestRepo.findByRequester(customerDb)

        if(requestDao!=null && requestModel.carId == requestDao.car.id && requestDao.status != RequestStatus.DONE) throw DefaultSupportedException("You have the same request already",403)

        val carDb = carRepo.findById(requestModel.carId).orElseThrow { throw SupportedMissMatchException("Car Not Found",404) }

        val requestDb = RequestDao().apply {
            this.requester = customerDb
            car = carDb
            description = requestModel.description
        }
        requestRepo.save(requestDb)

    }

    fun getCarList(): CarFilterListRequestModel {
        return CarFilterListRequestModel().apply {
            addAll(
                carRepo.findAll().map {
                    CarFilterListRequestModel.CarFilterItemRequestModel(it.brand,it.model,it.img_link)
                }
            )
        }

    }

    fun getSkillList(): SkillFilterListRequestModel {
        return SkillFilterListRequestModel().apply {
            addAll(
                skillRepo.findAll().map {
                    SkillFilterListRequestModel.SkillFilterItemRequestModel(it.name)
                }
            )
        }
    }

    fun getRequestList(userId: UUID): RequestListResponseModel {
        val customerDb = customerRepo.findById(userId).orElseThrow { DefaultSupportedException("Your Token has problem in userId",403) }
        return customerDb.requestList.map {itReq->
            RequestListResponseModel.RequestItem(itReq.repairMan.id,itReq.car,itReq.description,itReq.status)
        }.let {
            RequestListResponseModel().apply { addAll(it) }
        }
    }

    fun editProfile(userId: UUID,request: UserEditProfileRequestModel) {
        if(request.firstName.isNullOrBlank() && request.lastName.isNullOrBlank()) throw DefaultSupportedException("You must fill at least firstname or lastname",405)

        val userDb = customerRepo.findById(userId).orElseThrow { DefaultSupportedException("User id is not valid") }

        when {
            request.firstName.isNullOrBlank() && !request.lastName.isNullOrBlank() -> {
                userDb.firstname = request.firstName
            }
            !request.firstName.isNullOrBlank() && request.lastName.isNullOrBlank() -> {
                userDb.lastname = request.lastName
            }
            !request.firstName.isNullOrBlank() && !request.lastName.isNullOrBlank() -> {
                userDb.firstname = request.firstName
                userDb.lastname = request.lastName
            }
        }

        //TODO do a grpc for sync between sso and mainSystem

        customerRepo.save(userDb)
    }

    fun reportRepairMan(userId: UUID,request: ReportRequestModel) {
        val reporter = customerRepo.findById(userId).orElseThrow { DefaultSupportedException("Your id is not valid!",403) }

        val repairman =repairManRepo.findById(request.repairManId).orElseThrow { DefaultSupportedException("Your id is not valid!",403) }

        ReportDao().apply {
            setRepairMan(repairman)
            setReporter(reporter)
            setDescription(request.description)
        }.let { itReport->
            reportRepo.save(itReport)
        }

    }

}