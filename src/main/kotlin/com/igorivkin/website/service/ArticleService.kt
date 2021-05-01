package com.igorivkin.website.service

import com.igorivkin.website.model.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ArticleService : BaseService<Article, Long>  {
    fun findById(id: Long, withTopics: Boolean): Article
    fun findByTitle(title: String): List<Article>
    fun findAll(pageable: Pageable, withTopics: Boolean): Page<Article>
}