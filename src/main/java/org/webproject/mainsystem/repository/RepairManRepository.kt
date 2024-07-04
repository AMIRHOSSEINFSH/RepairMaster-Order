package org.webproject.mainsystem.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.webproject.mainsystem.model.dao.RepairManDao
import java.util.UUID

@Repository
interface RepairManRepository: JpaRepository<RepairManDao, UUID> {



}