package com.igorivkin.website.service

import com.igorivkin.website.model.User

interface UserService : BaseService<User, Long> {
    fun findByUsername(username: String): User?
}