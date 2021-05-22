package com.igorivkin.website.service

import com.igorivkin.website.model.Course
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CourseServiceTests {
    @Autowired
    private lateinit var courseService: CourseService

    //@Test
    fun createCourseTest() {
        val course = Course(
            title = "Тестовый курс",
            description = "Тестовое описание",
            orderedArticles = longArrayOf(1L, 2L)
        )
        val createdCourse = courseService.create(course)

        assertNotNull(createdCourse.id)
    }
}