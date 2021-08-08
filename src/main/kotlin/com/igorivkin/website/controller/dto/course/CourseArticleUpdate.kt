package com.igorivkin.website.controller.dto.course

data class CourseArticleUpdate(
    val courseId: Long,
    val articleId: Long,
    val order: Int
)