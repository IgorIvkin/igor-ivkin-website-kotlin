package com.igorivkin.website.service.mapper

import com.igorivkin.website.controller.dto.topic.TopicCreateRequest
import com.igorivkin.website.controller.dto.topic.TopicGetResponse
import com.igorivkin.website.controller.dto.topic.TopicUpdateRequest
import com.igorivkin.website.persistence.entity.Topic
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface TopicMapper {
    @Mappings(
        Mapping(source = "id", target = "id"),
        Mapping(source = "title", target = "title")
    )
    fun toDto(topic: Topic): TopicGetResponse
    fun toDto(topics: List<Topic>): List<TopicGetResponse>

    @Mappings(
        Mapping(source = "title", target = "title")
    )
    fun toModel(topic: TopicCreateRequest): Topic

    @Mappings(
        Mapping(source = "title", target = "title")
    )
    fun update(request: TopicUpdateRequest, @MappingTarget topic: Topic)
}