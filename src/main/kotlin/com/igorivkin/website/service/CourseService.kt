package com.igorivkin.website.service

import com.igorivkin.website.controller.dto.IdValue
import com.igorivkin.website.controller.dto.course.CourseCreateRequest
import com.igorivkin.website.controller.dto.course.CourseGetResponse
import com.igorivkin.website.controller.dto.course.CourseGetSimplifiedResponse
import com.igorivkin.website.controller.dto.course.CourseUpdateRequest

interface CourseService {
    fun findById(id: Long): CourseGetResponse
    fun findAll(): List<CourseGetSimplifiedResponse>

    fun create(request: CourseCreateRequest): IdValue<Long>
    fun update(id: Long, request: CourseUpdateRequest): IdValue<Long>
}