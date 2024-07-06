package org.webproject.mainsystem.model.dto.request

data class SearchRepairManRequestModel
    (val searchText: String?,val skillFilter: List<Long>?, val carFilter: List<Long>?)
