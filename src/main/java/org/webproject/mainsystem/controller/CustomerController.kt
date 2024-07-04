package org.webproject.mainsystem.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.webproject.mainsystem.model.dto.request.RequestRepairRequestModel
import org.webproject.mainsystem.model.dto.response.FilterListResponseModel
import org.webproject.mainsystem.model.dto.response.RepairManListModel
import org.webproject.mainsystem.service.CustomerService
import org.webproject.responsewrapper.custom.response.ResponseHandler.generateResponse

@RestController
@RequestMapping("/v1/customer")
class CustomerController @Autowired constructor(private val customerService: CustomerService) {

    @GetMapping("/getFilters")
    fun getFilters(): ResponseEntity<Any?> {

        val responseModel= FilterListResponseModel(customerService.getCarList(),customerService.getSkillList())
        return generateResponse("Ok",200,responseModel)
    }

    @PostMapping("/sendRequest")
    fun setRequest(@RequestBody requestModel: RequestRepairRequestModel): ResponseEntity<Any?> {
        //TODO need just to get user(customer here) id
//        customerService.addRepairRequest()
        return generateResponse<Any>("Ok",200,null)
    }


}