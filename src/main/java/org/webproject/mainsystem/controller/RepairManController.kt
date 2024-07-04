package org.webproject.mainsystem.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.webproject.mainsystem.model.dto.request.SearchRepairManRequestModel
import org.webproject.mainsystem.model.dto.response.RepairManListModel
import org.webproject.mainsystem.service.RepairManService
import org.webproject.responsewrapper.custom.response.ResponseHandler.generateResponse

@RestController
@RequestMapping("/v1/repairMan")
class RepairManController @Autowired constructor(private val repairManService: RepairManService)  {

    @GetMapping("/getRequestList")
    fun getRequestListOfRepairMan(): ResponseEntity<Any?> {
        //TODO
        repairManService
        return generateResponse<RepairManListModel>("",1,null)
    }

    @PostMapping("/search")
    fun searchRepairMan(@RequestBody requestModel: SearchRepairManRequestModel): ResponseEntity<Any?> {

        //TODO
        return generateResponse<RepairManListModel>("",1,null)
    }




}