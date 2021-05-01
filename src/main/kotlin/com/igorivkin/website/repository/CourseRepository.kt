package com.igorivkin.website.repository

import com.igorivkin.website.model.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository: JpaRepository<Course, Long> {
}