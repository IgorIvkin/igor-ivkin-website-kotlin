package com.igorivkin.website.service

import com.igorivkin.website.controller.dto.IdValue
import com.igorivkin.website.controller.dto.article.ArticleCreateRequest
import com.igorivkin.website.controller.dto.article.ArticleGetResponse
import com.igorivkin.website.controller.dto.article.ArticleGetSimplifiedResponse
import com.igorivkin.website.controller.dto.article.ArticleUpdateRequest
import com.igorivkin.website.exception.EntityDoesNotExistException
import com.igorivkin.website.persistence.repository.ArticleRepository
import com.igorivkin.website.service.mapper.ArticleMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArticleServiceImpl(
    private val articleRepository: ArticleRepository,
    private val articleMapper: ArticleMapper
) : ArticleService {

    @Transactional
    override fun findById(id: Long): ArticleGetResponse {
        val article = articleRepository.findById(id)
            .orElseThrow { EntityDoesNotExistException.ofArticleId(id) }
        return articleMapper.toDto(article)
    }

    @Transactional
    override fun findByTitle(title: String): List<ArticleGetSimplifiedResponse> {
        return articleMapper.toSimplifiedDto(
            articleRepository.findAllByTitleContainingIgnoreCase(title)
        )
    }

    @Transactional
    override fun findAllByTopicId(topicId: Long, pageable: Pageable): Page<ArticleGetResponse> {
        val page = articleRepository.findAllByTopicsId(topicId, pageable)
        return PageImpl(
            articleMapper.toDto(page.toList()),
            pageable,
            page.totalElements
        )
    }

    @Transactional
    override fun findAll(pageable: Pageable): Page<ArticleGetResponse> {
        val page = articleRepository.findAll(pageable)
        return PageImpl(
            articleMapper.toDto(page.toList()),
            pageable,
            page.totalElements
        )
    }

    @Transactional
    override fun create(request: ArticleCreateRequest): IdValue<Long> {
        val articleId = articleRepository.save(articleMapper.toModel(request)).id
        if (articleId != null) {
            return IdValue(id = articleId)
        } else {
            throw IllegalStateException("Cannot create request, ID was not set")
        }
    }

    @Transactional
    override fun update(id: Long, request: ArticleUpdateRequest): IdValue<Long>  {
        val article = articleRepository.findById(id)
            .orElseThrow { EntityDoesNotExistException.ofArticleId(id) }

        articleMapper.update(request, article)
        val articleId = articleRepository.save(article).id
        if (articleId != null) {
            return IdValue(id = articleId)
        } else {
            throw IllegalStateException("Cannot update request, cannot retrieve ID")
        }
    }
}