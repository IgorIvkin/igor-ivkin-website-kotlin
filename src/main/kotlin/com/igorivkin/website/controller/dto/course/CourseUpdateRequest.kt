package com.igorivkin.website.controller.dto.course

data class CourseUpdateRequest(
    val id: Long,
    val title: String,
    val description: String?,
    val articles: List<CourseArticleUpdate>
)