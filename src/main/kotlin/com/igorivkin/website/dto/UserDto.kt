package com.igorivkin.website.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.igorivkin.website.model.User
import com.igorivkin.website.model.UserRole
import java.time.Instant

data class UserDto (
    var id: Long? = null,
    var username: String,
    @JsonIgnore
    var password: String,
    var title: String? = null,
    var email: String? = null,
    var idOauth: String? = null,
    var createdAt: Instant? = null,
    var updatedAt: Instant? = null,
    var enabled: Boolean = true,
    var role: UserRole?
)