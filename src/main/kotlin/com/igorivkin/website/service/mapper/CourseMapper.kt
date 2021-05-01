package com.igorivkin.website.service.mapper

import com.igorivkin.website.converter.CourseConverter
import com.igorivkin.website.dto.CourseDto
import com.igorivkin.website.model.Course
import org.mapstruct.factory.Mappers

class CourseMapper {
    companion object {
        private val courseConverter: CourseConverter = Mappers.getMapper(CourseConverter::class.java)

        fun toDto(course: Course): CourseDto {
            return courseConverter.toDto(course)
        }

        fun toModel(courseDto: CourseDto): Course {
            return courseConverter.toModel(courseDto)
        }
    }
}