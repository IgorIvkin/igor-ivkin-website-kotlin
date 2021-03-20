package com.igorivkin.website.service

import com.igorivkin.website.converter.ArticleConverter
import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.model.Article
import com.igorivkin.website.repository.ArticleRepository
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl(
    private val articleRepository: ArticleRepository
):
    // extends
    BaseServiceImpl<Article, Long, ArticleDto>(
        articleRepository,
        Article::class.java,
        ArticleDto::class.java
    ),

    // implements
    ArticleService
{
    private val converter: ArticleConverter = Mappers.getMapper(ArticleConverter::class.java)

    override fun fromDto(dto: ArticleDto): Article {
        return converter.toModel(dto)
    }

    override fun toDto(entity: Article): ArticleDto {
        return converter.toDto(entity)
    }
}