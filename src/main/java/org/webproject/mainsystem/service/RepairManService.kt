package org.webproject.mainsystem.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.webproject.mainsystem.repository.RepairManRepository
import org.webproject.mainsystem.repository.RequestRepository
import java.util.UUID

@Service
class RepairManService @Autowired constructor (
    private val repairManRepo: RepairManRepository,
    private val requestRepo: RequestRepository
)
{


    fun getRequestListByRepairManId(repairManId: UUID) {
        //TODO

        requestRepo.findByRepairManId(repairManId)
    }

}