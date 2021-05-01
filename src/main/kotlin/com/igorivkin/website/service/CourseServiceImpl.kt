package com.igorivkin.website.service

import com.igorivkin.website.model.Course
import com.igorivkin.website.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository
) : BaseServiceImpl<Course, Long>(
        courseRepository,
        Course::class.java
    ),

    CourseService {

}