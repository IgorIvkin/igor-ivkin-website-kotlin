package com.igorivkin.website.service

import com.igorivkin.website.dto.UserDto
import com.igorivkin.website.model.User

interface UserService : BaseService<User, Long, UserDto> {
    fun findByUsername(username: String): User?
}