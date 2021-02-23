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
): BaseServiceImpl<User, Long, UserDto>(userRepository, User::class.java, UserDto::class.java), UserService {

    private val converter: UserConverter = Mappers.getMapper(UserConverter::class.java)

    override fun createFromDto(dto: UserDto): User {
        if (dto.password!!.isNotBlank()) {
            dto.password = passwordEncoder.encode(dto.password)
        }
        return super.createFromDto(dto)
    }

    override fun fromDto(dto: UserDto): User {
        return converter.toModel(dto)
    }

    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }
}