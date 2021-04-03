package com.igorivkin.website.mapper

import com.igorivkin.website.converter.TopicConverter
import com.igorivkin.website.dto.TopicDto
import com.igorivkin.website.model.Topic
import org.mapstruct.factory.Mappers

class TopicMapper {
    companion object {
        private val converter: TopicConverter = Mappers.getMapper(TopicConverter::class.java)

        fun toDto(topic: Topic): TopicDto {
            return converter.toDto(topic)
        }

        fun toModel(topicDto: TopicDto): Topic {
            return converter.toModel(topicDto)
        }
    }
}