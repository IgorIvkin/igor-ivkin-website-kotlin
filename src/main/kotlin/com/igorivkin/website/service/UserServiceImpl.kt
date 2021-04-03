package com.igorivkin.website.service

import com.igorivkin.website.model.User
import com.igorivkin.website.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
):
    // extends
    BaseServiceImpl<User, Long>(
        userRepository,
        User::class.java
    ),

    // implements
    UserService
{
    override fun create(entity: User): User {
        if (entity.password?.isNotBlank() == true) {
            entity.password = passwordEncoder.encode(entity.password)
        }
        return super.create(entity)
    }

    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }
}