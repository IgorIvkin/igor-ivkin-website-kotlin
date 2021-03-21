package com.igorivkin.website.dto

import java.time.Instant

data class ArticleDto(
    var id: Long? = null,
    var title: String,
    var content: String,
    var author: UserDto? = null,
    var createdAt: Instant?,
    var updatedAt: Instant?,
    var topics: List<TopicDto>? = mutableListOf<TopicDto>()
)