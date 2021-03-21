package com.igorivkin.website.converter

import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.model.Article
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget

@Mapper
interface ArticleConverter {
    @Mapping(source = "topics", target = "topics", ignore = true)
    fun toDto(article: Article): ArticleDto
    fun toModel(articleDto: ArticleDto): Article
}