package org.webproject.mainsystem.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.webproject.mainsystem.model.dao.AdminDao
import org.webproject.mainsystem.model.dao.CustomerDao
import org.webproject.mainsystem.model.dao.RepairManDao
import org.webproject.mainsystem.model.dao.requestDao.RequestDao
import java.util.UUID

@Repository
interface RequestRepository: JpaRepository<RequestDao, UUID> {

    fun findByRequester(customer: CustomerDao): RequestDao?

    fun findByRepairManId(repairId: UUID): RequestDao?

}