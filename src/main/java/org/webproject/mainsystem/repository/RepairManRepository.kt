package org.webproject.mainsystem.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.webproject.mainsystem.model.dao.RepairManDao
import java.util.*

@Repository
interface RepairManRepository: JpaRepository<RepairManDao, UUID> {

    @Query(
        "SELECT p FROM RepairMan p WHERE " +
                "(:name IS NULL OR :name = '' OR CONCAT(p.firstname, p.lastname) LIKE %:name%)"
    )
    fun findAllByName(@Param("name") name: String?): List<RepairManDao>?

}