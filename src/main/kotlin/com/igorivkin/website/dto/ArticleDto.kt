package com.igorivkin.website.dto

import com.igorivkin.website.model.Topic
import com.igorivkin.website.model.User
import java.time.Instant

data class ArticleDto(
    var id: Long? = null,
    var title: String,
    var content: String,
    var author: User? = null,
    var createdAt: Instant?,
    var updatedAt: Instant?,
    var topics: List<Topic>? = mutableListOf<Topic>()
)