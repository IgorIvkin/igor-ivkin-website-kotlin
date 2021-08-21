package com.igorivkin.website.controller.dto.course

data class CourseGetResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val articles: List<CourseArticleGetResponse>
)