package com.igorivkin.website.converter

import com.igorivkin.website.dto.UserDto
import com.igorivkin.website.model.User
import org.mapstruct.Mapper

@Mapper
interface UserConverter {
    fun toDto(user: User): UserDto
    fun toModel(userDto: UserDto): User
}