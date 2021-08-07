package com.igorivkin.website.service

import com.igorivkin.website.controller.dto.topic.TopicCreateRequest
import com.igorivkin.website.controller.dto.topic.TopicGetResponse
import com.igorivkin.website.controller.dto.topic.TopicUpdateRequest

interface TopicService {
    fun findById(id: Long): TopicGetResponse
    fun findAll(): List<TopicGetResponse>
    fun findByTitle(title: String): List<TopicGetResponse>

    fun create(request: TopicCreateRequest): TopicGetResponse
    fun update(id: Long, request: TopicUpdateRequest): TopicGetResponse

}