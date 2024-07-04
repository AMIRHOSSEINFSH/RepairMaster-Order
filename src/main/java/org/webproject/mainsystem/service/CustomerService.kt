package org.webproject.mainsystem.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.webproject.mainsystem.model.dao.requestDao.RequestDao
import org.webproject.mainsystem.model.dto.request.CarFilterListRequestModel
import org.webproject.mainsystem.model.dto.request.RequestRepairRequestModel
import org.webproject.mainsystem.model.dto.request.SkillFilterListRequestModel
import org.webproject.mainsystem.model.enumData.RequestStatus
import org.webproject.mainsystem.repository.CustomerRepository
import org.webproject.mainsystem.repository.RequestRepository
import org.webproject.mainsystem.repository.SkillRepository
import org.webproject.mainsystem.repository.SupportedCarRepository
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
    private val skillRepo: SkillRepository
) {

    fun addRepairRequest(customer: UUID, requestModel: RequestRepairRequestModel) {
        val customerDb= customerRepo.findById(customer).orElseThrow { throw SupportedMissMatchException("User Not Found",404) }

        if(LocalDateTime.ofEpochSecond(requestModel.to_Date,0, ZoneOffset.UTC).isBefore(LocalDateTime.now())) throw SupportedMissMatchException("Your Time is passed",405)

        val requestDao = requestRepo.findByRequester(customerDb)

        if(requestDao!=null && requestModel.carId == requestDao.car.id && requestDao.status != RequestStatus.DONE) throw DefaultSupportedException("You have the same request already",403)

        val carDb = carRepo.findById(requestModel.carId).orElseThrow { throw SupportedMissMatchException("Car Not Found",404) }

        val requestDb = RequestDao.builder().apply {
            this.requester(customerDb)
            car(carDb)
            description(requestModel.description)
        }.build()
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

}