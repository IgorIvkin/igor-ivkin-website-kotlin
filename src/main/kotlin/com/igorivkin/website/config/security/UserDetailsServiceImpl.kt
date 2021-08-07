package com.igorivkin.website.config.security

import com.igorivkin.website.persistence.entity.User
import com.igorivkin.website.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service(value = "userDetailsService")
class UserDetailsServiceImpl: UserDetailsService {

    @Autowired
    private lateinit var userService: UserService

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String?): UserDetails {
        val user: User = username?.let { userService.findByUsername(it) } ?: throw IllegalArgumentException("Cannot load user by user name $username")
        return LocalUserDetails(user)
    }
}