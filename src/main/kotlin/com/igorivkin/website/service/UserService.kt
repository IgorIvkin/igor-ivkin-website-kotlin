package com.igorivkin.website.service

import com.igorivkin.website.persistence.entity.User

interface UserService {
    fun findById(id: Long): User?
    fun findByUsername(username: String): User?
}