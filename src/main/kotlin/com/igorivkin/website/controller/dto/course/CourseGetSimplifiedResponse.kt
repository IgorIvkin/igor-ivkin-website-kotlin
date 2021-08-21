package com.igorivkin.website.controller.dto.course

data class CourseGetSimplifiedResponse(
    val id: Long,
    val title: String,
    val description: String?
)