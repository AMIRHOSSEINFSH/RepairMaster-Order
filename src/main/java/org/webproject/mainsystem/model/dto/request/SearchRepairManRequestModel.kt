package org.webproject.mainsystem.model.dto.request

data class SearchRepairManRequestModel(val searchText: String,val skillFilter: List<Int>, val carFilter: List<Int>)
