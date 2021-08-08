package com.igorivkin.website.persistence.repository

import com.igorivkin.website.persistence.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository: JpaRepository<Course, Long> {
}