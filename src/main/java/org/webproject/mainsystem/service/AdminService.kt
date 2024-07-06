package org.webproject.mainsystem.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.webproject.mainsystem.model.dao.RepairManDao
import org.webproject.mainsystem.model.dao.ReportDao
import org.webproject.mainsystem.model.dao.SkillDao
import org.webproject.mainsystem.model.dao.SupportedCarDao
import org.webproject.mainsystem.model.dao.RequestDao
import org.webproject.mainsystem.model.dto.request.CarFilterListRequestModel
import org.webproject.mainsystem.model.dto.request.RequestStatusRequestModel
import org.webproject.mainsystem.model.dto.request.SkillFilterListRequestModel
import org.webproject.mainsystem.model.enumData.RequestStatus.*
import org.webproject.mainsystem.repository.*
import org.webproject.responsewrapper.custom.exception.DefaultSupportedException

@Service
class AdminService @Autowired constructor(
    private val adminRepo: AdminRepository,
    private val repairRepo: RepairManRepository,
    private val carRepo: SupportedCarRepository,
    private val skillRepo: SkillRepository,
    private val requestRepository: RequestRepository,
    private val reportRepo: ReportRepository
) {

    fun getRepairManList(): List<RepairManDao> {
        //TODO need to be mapped to a dto object
        return repairRepo.findAll()
    }


    fun addCarType(requestModel: CarFilterListRequestModel) {
        if (requestModel.any { it.brand.isNullOrBlank() }) {
            throw DefaultSupportedException("car model is required", 402)
        }
        requestModel.map { itReq->
            SupportedCarDao().apply {
                model = itReq.model
                brand = itReq.brand
                img_link = itReq.img_link
            }
        }.apply {
            carRepo.saveAll(this)
        }

    }

    fun addSkillType(requestModel: SkillFilterListRequestModel) {
        if (requestModel.any { it.name.isNullOrBlank() }) {
            throw DefaultSupportedException("car model is required", 402)
        }

        requestModel.map { itReq->
            SkillDao().apply {
                name = itReq.name
            }
        }.apply {
            skillRepo.saveAll(this)
        }
    }

    fun getAllRequestList(): List<RequestDao> {
        return requestRepository.findAll()
    }

    @Transactional
    fun changeRequestStatus(requestModel: RequestStatusRequestModel) {
        val resultStatus= if (requestModel.isAccepted) ADMIN_ACCEPTED else ADMIN_REJECTED
        requestRepository.changeStatusOfRequestList(status = resultStatus,ids = requestModel.requestIds)
    }

    fun getAllReportList(): List<ReportDao> {
        return reportRepo.findAll()
    }

}