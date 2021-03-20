package com.igorivkin.website.service

import com.igorivkin.website.converter.TopicConverter
import com.igorivkin.website.dto.TopicDto
import com.igorivkin.website.model.Topic
import com.igorivkin.website.repository.TopicRepository
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class TopicServiceImpl(
    private val topicRepository: TopicRepository
):
    // extends
    BaseServiceImpl<Topic, Long, TopicDto>(
        topicRepository,
        Topic::class.java,
        TopicDto::class.java
    ),

    // implements
    TopicService
{

    private val converter: TopicConverter = Mappers.getMapper(TopicConverter::class.java)

    override fun fromDto(dto: TopicDto): Topic {
        return converter.toModel(dto)
    }

    override fun toDto(entity: Topic): TopicDto {
        return converter.toDto(entity)
    }

    override fun findByTitle(title: String): List<Topic> {
        return topicRepository.findByTitleStartingWithIgnoreCase(title)
    }
}