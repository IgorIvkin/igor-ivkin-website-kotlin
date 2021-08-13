package com.igorivkin.website.service

import com.igorivkin.website.controller.dto.IdValue
import com.igorivkin.website.controller.dto.article.ArticleCreateRequest
import com.igorivkin.website.controller.dto.article.ArticleGetResponse
import com.igorivkin.website.controller.dto.article.ArticleGetSimplifiedResponse
import com.igorivkin.website.controller.dto.article.ArticleUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ArticleService  {
    fun findById(id: Long): ArticleGetResponse
    fun findByTitle(title: String): List<ArticleGetSimplifiedResponse>
    fun findAllByTopicId(topicId: Long, pageable: Pageable): Page<ArticleGetResponse>
    fun findAll(pageable: Pageable): Page<ArticleGetResponse>
    fun findAll(pageNum: Int, articlesPerPage: Int): Page<ArticleGetResponse>

    fun create(request: ArticleCreateRequest): IdValue<Long>
    fun update(id: Long, request: ArticleUpdateRequest): IdValue<Long>
}