package com.igorivkin.website.converter

import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.persistence.entity.Article
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface ArticleConverter {
    @Mapping(source = "topics", target = "topics", ignore = true)
    fun toDto(article: Article): ArticleDto
    fun toModel(articleDto: ArticleDto): Article
}