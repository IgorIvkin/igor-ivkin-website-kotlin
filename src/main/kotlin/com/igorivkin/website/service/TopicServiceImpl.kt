package com.igorivkin.website.service

import com.igorivkin.website.controller.dto.topic.TopicCreateRequest
import com.igorivkin.website.controller.dto.topic.TopicGetResponse
import com.igorivkin.website.controller.dto.topic.TopicUpdateRequest
import com.igorivkin.website.exception.EntityDoesNotExistException
import com.igorivkin.website.persistence.repository.TopicRepository
import com.igorivkin.website.service.mapper.TopicMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TopicServiceImpl(
    private val topicRepository: TopicRepository,
    private val topicMapper: TopicMapper
): TopicService {

    @Transactional
    override fun findById(id: Long): TopicGetResponse {
        val topic = topicRepository.findById(id)
            .orElseThrow { EntityDoesNotExistException.ofTopicId(id) }
        return topicMapper.toDto(topic)
    }

    @Transactional
    override fun findAll(): List<TopicGetResponse> {
        return topicMapper.toDto(
            topicRepository.findAll()
        )
    }

    @Transactional
    override fun findByTitle(title: String): List<TopicGetResponse> {
        return topicMapper.toDto(
            topicRepository.findByTitleStartingWithIgnoreCase(title)
        )
    }

    @Transactional
    override fun create(request: TopicCreateRequest): TopicGetResponse {
        return topicMapper.toDto(
            topicRepository.save(topicMapper.toModel(request))
        )
    }

    @Transactional
    override fun update(id: Long, request: TopicUpdateRequest): TopicGetResponse {
        val topic = topicRepository.findById(id)
            .orElseThrow { EntityDoesNotExistException.ofTopicId(id) }

        topicMapper.update(request, topic)
        return topicMapper.toDto(topicRepository.save(topic))
    }
}