package com.igorivkin.website.service

import com.igorivkin.website.converter.UserConverter
import com.igorivkin.website.dto.UserDto
import com.igorivkin.website.model.User
import com.igorivkin.website.repository.UserRepository
import org.mapstruct.factory.Mappers
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
):
    // extends
    BaseServiceImpl<User, Long, UserDto>(
        userRepository,
        User::class.java,
        UserDto::class.java
    ),

    // implements
    UserService {

    private val converter: UserConverter = Mappers.getMapper(UserConverter::class.java)

    override fun create(entity: User): User {
        if (entity.password.isNotBlank()) {
            entity.password = passwordEncoder.encode(entity.password)
        }
        return super.create(entity)
    }

    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    override fun fromDto(dto: UserDto): User {
        return converter.toModel(dto)
    }

    override fun toDto(entity: User): UserDto {
        return converter.toDto(entity)
    }
}