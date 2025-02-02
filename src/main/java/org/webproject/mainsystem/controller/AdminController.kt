package org.webproject.mainsystem.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.webproject.mainsystem.model.dto.request.CarFilterListRequestModel
import org.webproject.mainsystem.model.dto.request.RequestStatusRequestModel
import org.webproject.mainsystem.model.dto.request.SkillFilterListRequestModel
import org.webproject.mainsystem.service.AdminService
import org.webproject.responsewrapper.custom.response.ResponseHandler.generateResponse

@RestController
@RequestMapping("/v1/admin")
class AdminController @Autowired constructor(val adminService: AdminService) {

    @GetMapping("/getRepairManList")
    fun getRepairManList(): ResponseEntity<Any?> {
        return generateResponse("Ok",200,adminService.getRepairManList())
    }

    @PostMapping("/filter/addCarType")
    fun addCarFilter(@RequestBody requestModel: CarFilterListRequestModel): ResponseEntity<Any?> {
        adminService.addCarType(requestModel)

        return generateResponse("Ok",200,null)
    }

    @PostMapping("/filter/addSkillType")
    fun addSkillFilter(@RequestBody requestModel: SkillFilterListRequestModel): ResponseEntity<Any?> {
        adminService.addSkillType(requestModel)

        return generateResponse("Ok",200,null)
    }

    @GetMapping("/getAllRequestList")
    fun getAllRequestList(): ResponseEntity<Any?> {
        return generateResponse("Ok",200,adminService.getAllRequestList())
    }
    //DONE TODO admin accept / rege requests

    @PostMapping("/changeReqStatus")
    fun changeStatus(@RequestBody requestModel: RequestStatusRequestModel) : ResponseEntity<Any?>{
        adminService.changeRequestStatus(requestModel)
        return generateResponse("Ok",200,null)
    }

    @GetMapping("/getAllReportList")
    fun getAllReportList(): ResponseEntity<Any?> {
        return generateResponse("Ok",200,adminService.getAllReportList())
    }

}