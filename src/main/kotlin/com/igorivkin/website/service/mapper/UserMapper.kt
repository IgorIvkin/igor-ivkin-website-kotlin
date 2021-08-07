package com.igorivkin.website.service.mapper

import com.igorivkin.website.controller.dto.user.UserGetResponse
import com.igorivkin.website.persistence.entity.User
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserMapper {
    fun toDto(user: User): UserGetResponse
}