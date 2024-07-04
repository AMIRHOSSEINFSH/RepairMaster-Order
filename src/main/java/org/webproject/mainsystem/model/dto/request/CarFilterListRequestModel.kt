package org.webproject.mainsystem.model.dto.request


class CarFilterListRequestModel: ArrayList<CarFilterListRequestModel.CarFilterItemRequestModel>(){
    class CarFilterItemRequestModel(val brand: String,val model: String?,val img_link: String?)

}