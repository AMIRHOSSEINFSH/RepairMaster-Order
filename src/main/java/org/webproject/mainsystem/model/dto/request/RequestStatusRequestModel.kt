package org.webproject.mainsystem.model.dto.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

data class RequestStatusRequestModel(
    @field:NotNull(message = "parameter isAccepted is Required") val isAccepted: Boolean,
    @field:NotNull(message = "parameter requestIds is Required")
    @field:NotEmpty(message = "parameter requestIds must not be empty")
    val requestIds: List<UUID>
)