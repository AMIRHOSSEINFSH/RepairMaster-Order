package org.webproject.mainsystem.model.dto.response

import org.webproject.mainsystem.model.dto.request.CarFilterListRequestModel
import org.webproject.mainsystem.model.dto.request.SkillFilterListRequestModel

data class FilterListResponseModel(val carList: CarFilterListRequestModel,val skillList: SkillFilterListRequestModel)