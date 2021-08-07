package com.igorivkin.website.controller.dto.article

import com.igorivkin.website.controller.dto.topic.TopicGetResponse
import com.igorivkin.website.controller.dto.user.UserGetResponse
import java.time.Instant

data class ArticleGetResponse(
    val id: Long,
    val title: String,
    val content: String,
    val author: UserGetResponse,
    val createdAt: Instant,
    val updatedAt: Instant,
    val topics: List<TopicGetResponse>
)