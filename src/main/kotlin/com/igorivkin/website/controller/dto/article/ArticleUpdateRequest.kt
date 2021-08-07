package com.igorivkin.website.controller.dto.article

import com.igorivkin.website.controller.dto.IdValue

data class ArticleUpdateRequest(
    val id: Long,
    val title: String,
    val content: String,
    val author: IdValue<Long>,
    val topics: List<IdValue<Long>>
)