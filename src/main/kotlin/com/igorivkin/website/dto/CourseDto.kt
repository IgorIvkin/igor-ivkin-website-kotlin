package com.igorivkin.website.dto

data class CourseDto(
    var id: Long? = null,
    var title: String,
    var description: String?,
    var articles: List<ArticleDto>? = mutableListOf<ArticleDto>()
)