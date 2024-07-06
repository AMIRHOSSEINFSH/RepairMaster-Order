package org.webproject.mainsystem.model.dto.request

import org.webproject.mainsystem.model.validator.workExp.ValidWorkExp

class UserEditProfileRequestModel
    (val firstName: String?,
     val lastName: String?,
     val supportedCarIds: List<Long>?,
     @ValidWorkExp val workExperience: Int?
            )
