package org.webproject.mainsystem.model.dto.response

import org.webproject.mainsystem.model.dao.SupportedCarDao
import org.webproject.mainsystem.model.enumData.RequestStatus
import java.util.UUID

class RequestListResponseModel: ArrayList<RequestListResponseModel.RequestItem>() {
    data class RequestItem(val repairManId: UUID?, val car: SupportedCarDao,val description: String?,val status: RequestStatus)
}