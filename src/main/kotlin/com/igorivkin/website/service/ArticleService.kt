package com.igorivkin.website.service

import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.model.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ArticleService : BaseService<Article, Long, ArticleDto>  {
    fun findById(id: Long, withTopics: Boolean): Article
    fun findAll(pageable: Pageable, withTopics: Boolean): Page<Article>

    fun toDtoWithTopics(entity: Article): ArticleDto
    fun toListOfDtoWithTopics(entityList: List<Article>): List<ArticleDto>
}