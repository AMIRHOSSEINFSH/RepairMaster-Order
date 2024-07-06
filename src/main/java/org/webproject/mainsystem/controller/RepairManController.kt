package org.webproject.mainsystem.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.webproject.mainsystem.model.dto.request.SearchRepairManRequestModel
import org.webproject.mainsystem.model.dto.request.UserEditProfileRequestModel
import org.webproject.mainsystem.model.dto.response.RepairManListModel
import org.webproject.mainsystem.service.RepairManService
import org.webproject.mainsystem.util.getTokenInfoFromHeader
import org.webproject.responsewrapper.custom.response.ResponseHandler.generateResponse

@RestController
@RequestMapping("/v1/repairMan")
class RepairManController @Autowired constructor(private val repairManService: RepairManService)  {

    @GetMapping("/getRequestList")
    fun getRequestListOfRepairMan( requestServlet: HttpServletRequest): ResponseEntity<Any?> {
        val tokenInfo = requestServlet.getTokenInfoFromHeader()
        //DONE TODO
        return generateResponse("Ok",200,repairManService.getRequestListByRepairManId(tokenInfo.userId))
    }

    @PostMapping("/search")
    fun searchRepairMan(@RequestBody requestModel: SearchRepairManRequestModel): ResponseEntity<Any?> {

        //DONE TODO
        return generateResponse("Ok",200,repairManService.searchInRepairManList(requestModel))
    }

    //TODO edit profile info
    @PostMapping("/editProfile")
    fun editCustomerProfile(@RequestBody request: UserEditProfileRequestModel, requestServlet: HttpServletRequest): ResponseEntity<Any?> {
        val tokenInfo = requestServlet.getTokenInfoFromHeader()
        repairManService.editProfile(tokenInfo.userId,request)
        return generateResponse("Ok",200,null)
    }



}