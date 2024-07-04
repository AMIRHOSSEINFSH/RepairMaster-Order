package org.webproject.mainsystem.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.webproject.mainsystem.model.dao.RepairManDao
import org.webproject.mainsystem.model.dao.SkillDao
import org.webproject.mainsystem.model.dao.SupportedCarDao
import org.webproject.mainsystem.model.dto.request.CarFilterListRequestModel
import org.webproject.mainsystem.model.dto.request.SkillFilterListRequestModel
import org.webproject.mainsystem.repository.AdminRepository
import org.webproject.mainsystem.repository.RepairManRepository
import org.webproject.mainsystem.repository.SkillRepository
import org.webproject.mainsystem.repository.SupportedCarRepository
import org.webproject.responsewrapper.custom.exception.DefaultSupportedException

@Service
class AdminService @Autowired constructor(
    private val adminRepo: AdminRepository,
    private val repairRepo: RepairManRepository,
    private val carRepo: SupportedCarRepository,
    private val skillRepo: SkillRepository
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
            SupportedCarDao.builder()
                .model(itReq.model)
                .brand(itReq.brand)
                .img_link(itReq.img_link)
                .build()
        }.apply {
            carRepo.saveAll(this)
        }

    }

    fun addSkillType(requestModel: SkillFilterListRequestModel) {
        if (requestModel.any { it.name.isNullOrBlank() }) {
            throw DefaultSupportedException("car model is required", 402)
        }

        requestModel.map { itReq->
            SkillDao.builder()
                .name(itReq.name)
                .build()
        }.apply {
            skillRepo.saveAll(this)
        }
    }

}