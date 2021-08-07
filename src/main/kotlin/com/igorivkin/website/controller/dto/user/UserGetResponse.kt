package com.igorivkin.website.controller.dto.user

import com.igorivkin.website.persistence.entity.UserRole
import java.time.Instant

data class UserGetResponse(
    val id: Long,
    val username: String,
    val title: String,
    val email: String?,
    val oauthId: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
    val role: UserRole?,
)