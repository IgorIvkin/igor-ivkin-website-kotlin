package com.igorivkin.website.service

import com.igorivkin.website.model.Topic

interface TopicService : BaseService<Topic, Long> {
    fun findByTitle(title: String): List<Topic>
}