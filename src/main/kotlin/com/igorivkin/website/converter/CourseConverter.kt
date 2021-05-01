package com.igorivkin.website.converter

import com.igorivkin.website.dto.CourseDto
import com.igorivkin.website.model.Course
import org.mapstruct.Mapper

@Mapper
interface CourseConverter {
    fun toDto(course: Course): CourseDto
    fun toModel(courseDto: CourseDto): Course
}