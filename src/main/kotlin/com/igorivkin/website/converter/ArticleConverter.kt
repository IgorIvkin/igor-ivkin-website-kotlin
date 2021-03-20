package com.igorivkin.website.converter

import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.model.Article
import org.mapstruct.Mapper

@Mapper
interface ArticleConverter {
    fun toDto(topic: Article): ArticleDto
    fun toModel(topicDto: ArticleDto): Article
}