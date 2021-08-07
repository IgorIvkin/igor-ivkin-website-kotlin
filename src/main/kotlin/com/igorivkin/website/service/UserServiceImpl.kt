package com.igorivkin.website.service

import com.igorivkin.website.exception.EntityDoesNotExistException
import com.igorivkin.website.persistence.entity.User
import com.igorivkin.website.persistence.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): UserService {

    override fun findById(id: Long): User? {
        return userRepository.findById(id)
            .orElseThrow { EntityDoesNotExistException.ofUserId(id) }
    }

    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    /*override fun create(entity: User): User {
        if (entity.password?.isNotBlank() == true) {
            entity.password = passwordEncoder.encode(entity.password)
        }
        return super.create(entity)
    }*/

}