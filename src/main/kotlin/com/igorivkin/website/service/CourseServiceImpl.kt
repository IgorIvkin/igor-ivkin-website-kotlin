package com.igorivkin.website.service

import com.igorivkin.website.controller.dto.IdValue
import com.igorivkin.website.controller.dto.course.CourseCreateRequest
import com.igorivkin.website.controller.dto.course.CourseGetResponse
import com.igorivkin.website.controller.dto.course.CourseGetSimplifiedResponse
import com.igorivkin.website.controller.dto.course.CourseUpdateRequest
import com.igorivkin.website.exception.EntityDoesNotExistException
import com.igorivkin.website.persistence.repository.CourseRepository
import com.igorivkin.website.service.mapper.CourseMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val courseMapper: CourseMapper
): CourseService {

    @Transactional
    override fun findById(id: Long): CourseGetResponse {
        val course = courseRepository.findById(id)
            .orElseThrow { EntityDoesNotExistException.ofCourseId(id) }
        return courseMapper.toDto(course)
    }

    @Transactional
    override fun findAll(): List<CourseGetSimplifiedResponse> {
        return courseMapper.toSimplifiedDto(
            courseRepository.findAll()
        )
    }

    @Transactional
    override fun create(request: CourseCreateRequest): IdValue<Long> {
        val courseId = courseRepository.save(courseMapper.toModel(request)).id
        if (courseId != null) {
            return IdValue(id = courseId)
        } else {
            throw IllegalStateException("Cannot create course, ID not exists")
        }
    }

    @Transactional
    override fun update(id: Long, request: CourseUpdateRequest): IdValue<Long> {
        val course = courseRepository.findById(id)
            .orElseThrow { EntityDoesNotExistException.ofCourseId(id) }

        courseMapper.update(request, course)
        val courseId = courseRepository.save(course).id
        if (courseId != null) {
            return IdValue(id = courseId)
        } else {
            throw IllegalStateException("Cannot update course, cannot retrieve ID")
        }
    }


}