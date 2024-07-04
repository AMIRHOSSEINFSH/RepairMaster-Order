package org.webproject.mainsystem.model.dto.request

class SkillFilterListRequestModel: ArrayList<SkillFilterListRequestModel.SkillFilterItemRequestModel>(){
    class SkillFilterItemRequestModel(val name: String)
}