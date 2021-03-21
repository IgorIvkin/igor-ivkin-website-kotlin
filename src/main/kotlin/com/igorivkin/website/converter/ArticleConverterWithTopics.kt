package com.igorivkin.website.converter

import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.model.Article
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper
interface ArticleConverterWithTopics {
    fun toDto(article: Article): ArticleDto
    fun toModel(articleDto: ArticleDto): Article

    @InheritInverseConfiguration(name = "toDto")
    fun fromDto(articleDto: ArticleDto, @MappingTarget article: Article)
}