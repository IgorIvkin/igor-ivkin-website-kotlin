package com.igorivkin.website.service

import com.igorivkin.website.model.Topic
import com.igorivkin.website.repository.TopicRepository
import org.springframework.stereotype.Service

@Service
class TopicServiceImpl(
    private val topicRepository: TopicRepository
):
    // extends
    BaseServiceImpl<Topic, Long>(
        topicRepository,
        Topic::class.java
    ),

    // implements
    TopicService
{

    override fun findByTitle(title: String): List<Topic> {
        return topicRepository.findByTitleStartingWithIgnoreCase(title)
    }
}