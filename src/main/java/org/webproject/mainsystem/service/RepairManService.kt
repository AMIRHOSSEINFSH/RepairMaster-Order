package org.webproject.mainsystem.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.webproject.mainsystem.model.dao.RepairManDao
import org.webproject.mainsystem.model.dao.RequestDao
import org.webproject.mainsystem.model.dto.request.SearchRepairManRequestModel
import org.webproject.mainsystem.model.dto.request.UserEditProfileRequestModel
import org.webproject.mainsystem.model.enumData.WorkExperience
import org.webproject.mainsystem.repository.RepairManRepository
import org.webproject.mainsystem.repository.RequestRepository
import org.webproject.mainsystem.repository.SupportedCarRepository
import org.webproject.responsewrapper.custom.exception.DefaultSupportedException
import java.util.*

@Service
class RepairManService @Autowired constructor (
    private val repairManRepo: RepairManRepository,
    private val requestRepo: RequestRepository,
    private val supportedCarRepository: SupportedCarRepository
)
{

    fun getRequestListByRepairManId(repairManId: UUID): RequestDao {

        return requestRepo.findByRepairManId(repairManId) ?: throw DefaultSupportedException("User not found!", 404)
    }

    fun editProfile(userId: UUID, request: UserEditProfileRequestModel) {
        val workExp = WorkExperience.getWorkExperienceTypeById(request.workExperience ?: -1)
        if (request.firstName.isNullOrBlank() && request.lastName.isNullOrBlank() && WorkExperience.getWorkExperienceTypeById(
                request.workExperience ?: -1
            ) == null && request.supportedCarIds.isNullOrEmpty()
        ) throw DefaultSupportedException("You must change at least something :)", 405)

        val userDb = repairManRepo.findById(userId).orElseThrow { DefaultSupportedException("User id is not valid") }

        var shouldSyncWithSso = false
        when {
            request.firstName.isNullOrBlank() && !request.lastName.isNullOrBlank() -> {
                userDb.firstname = request.firstName
                shouldSyncWithSso = true
            }

            !request.firstName.isNullOrBlank() && request.lastName.isNullOrBlank() -> {
                userDb.lastname = request.lastName
                shouldSyncWithSso = true
            }

            !request.firstName.isNullOrBlank() && !request.lastName.isNullOrBlank() -> {
                userDb.firstname = request.firstName
                userDb.lastname = request.lastName
                shouldSyncWithSso = true
            }
        }

        when {
            workExp == null && !request.supportedCarIds.isNullOrEmpty() -> {
                userDb.supportedCarList = supportedCarRepository.findAllById(request.supportedCarIds).toSet()
            }

            workExp != null && request.supportedCarIds.isNullOrEmpty() -> {
                userDb.workExperience = workExp
            }

            workExp != null && !request.supportedCarIds.isNullOrEmpty() -> {
                userDb.supportedCarList = supportedCarRepository.findAllById(request.supportedCarIds).toSet()
                userDb.workExperience = workExp
            }
        }


        if (shouldSyncWithSso) {
            //TODO do a grpc for sync between sso and mainSystem
        }

        repairManRepo.save(userDb)
    }

    fun searchInRepairManList(filter: SearchRepairManRequestModel): List<RepairManDao> {

        val repairManList = repairManRepo.findAllByName(filter.searchText.orEmpty()).orEmpty()
        return repairManList.filter {
            it.skillList.map { it.id }.any { filter.skillFilter?.contains(it) ?: true }
                    &&
                    it.supportedCarList.map { it.id }.any { filter.carFilter?.contains(it) ?: true }
        }


    }

}