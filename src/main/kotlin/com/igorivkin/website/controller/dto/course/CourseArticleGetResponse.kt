package com.igorivkin.website.controller.dto.course

data class CourseArticleGetResponse(
    val courseId: Long,
    val articleId: Long,
    val articleTitle: String,
    val order: Int
)