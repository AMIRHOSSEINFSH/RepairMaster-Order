package org.webproject.mainsystem.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.webproject.mainsystem.model.dao.CustomerDao
import org.webproject.mainsystem.model.dao.RequestDao
import org.webproject.mainsystem.model.enumData.RequestStatus
import java.util.UUID

@Repository
interface RequestRepository: JpaRepository<RequestDao, UUID> {

    fun findByRequester(customer: CustomerDao): RequestDao?

    fun findByRepairManId(repairId: UUID): RequestDao?

    @Modifying
    @Transactional
    @Query("UPDATE Request e SET e.status= :status where e.requestId IN :ids")
    fun changeStatusOfRequestList(@Param("status") status: RequestStatus, @Param("ids") ids: List<UUID>)
}