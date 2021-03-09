package com.igorivkin.website.converter

import com.igorivkin.website.dto.TopicDto
import com.igorivkin.website.model.Topic
import org.mapstruct.Mapper

@Mapper
interface TopicConverter {
    fun toDto(topic: Topic): TopicDto
    fun toModel(topicDto: TopicDto): Topic
}