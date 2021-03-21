package com.igorivkin.website.converter

import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.model.Article
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface ArticleConverter {
    @Mapping(source = "topics", target = "topics", ignore = true)
    fun toDto(topic: Article): ArticleDto
    fun toModel(topicDto: ArticleDto): Article
}