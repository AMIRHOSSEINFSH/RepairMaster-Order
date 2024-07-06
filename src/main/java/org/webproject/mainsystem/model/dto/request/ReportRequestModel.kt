package org.webproject.mainsystem.model.dto.request

import jakarta.validation.constraints.NotNull
import java.util.UUID

data class ReportRequestModel(@field:NotNull(message = "You must Say Who you want to report") val repairManId: UUID,val description: String?)