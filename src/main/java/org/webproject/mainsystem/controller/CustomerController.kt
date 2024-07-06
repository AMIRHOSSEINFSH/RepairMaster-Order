package org.webproject.mainsystem.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.webproject.mainsystem.model.dto.request.ReportRequestModel
import org.webproject.mainsystem.model.dto.request.RequestRepairRequestModel
import org.webproject.mainsystem.model.dto.request.UserEditProfileRequestModel
import org.webproject.mainsystem.model.dto.response.FilterListResponseModel
import org.webproject.mainsystem.service.CustomerService
import org.webproject.mainsystem.util.getTokenInfoFromHeader
import org.webproject.responsewrapper.custom.exception.DefaultSupportedException
import org.webproject.responsewrapper.custom.response.ResponseHandler.generateResponse
import org.webproject.responsewrapper.enumModel.USERTYPE

@RestController
@RequestMapping("/v1/customer")
class CustomerController @Autowired constructor(private val customerService: CustomerService) {

    @GetMapping("/getFilters")
    fun getFilters(): ResponseEntity<Any?> {
        val responseModel= FilterListResponseModel(customerService.getCarList(),customerService.getSkillList())
        return generateResponse("Ok",200,responseModel)
    }

    @PostMapping("/sendRequest")
    fun setRequest(@RequestBody requestModel: RequestRepairRequestModel,requestServlet: HttpServletRequest): ResponseEntity<Any?> {
        val tokenInfo = requestServlet.getTokenInfoFromHeader()
        customerService.addRepairRequest(tokenInfo.userId,requestModel)
        return generateResponse<Any>("Ok",200,null)
    }

    ///DONE get requests of user
    @GetMapping("/getRequestList")
    fun getUserRequestList(requestServlet: HttpServletRequest): ResponseEntity<Any?> {
        val tokenInfo = requestServlet.getTokenInfoFromHeader()
        return generateResponse("Ok",200,customerService.getRequestList(tokenInfo.userId))
    }

    //semi done: TODO edit profile for customer
    @PostMapping("/editProfile")
    fun editCustomerProfile(@RequestBody request: UserEditProfileRequestModel,requestServlet: HttpServletRequest): ResponseEntity<Any?> {
        val tokenInfo = requestServlet.getTokenInfoFromHeader()
        customerService.editProfile(tokenInfo.userId,request)
        return generateResponse("Ok",200,null)
    }

    @PostMapping("/reportUser")
    fun reportUser(@RequestBody request: ReportRequestModel,requestServlet: HttpServletRequest): ResponseEntity<Any?> {
        val tokenInfo = requestServlet.getTokenInfoFromHeader()
        if(tokenInfo.type == USERTYPE.REPAIRMAN)
            customerService.reportRepairMan(tokenInfo.userId,request)
        else throw DefaultSupportedException("You dont have access to this endpoint!",403)
        return generateResponse("Ok",200,null)
    }


}