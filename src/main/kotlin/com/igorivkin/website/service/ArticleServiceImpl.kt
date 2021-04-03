package com.igorivkin.website.service

import com.igorivkin.website.model.Article
import com.igorivkin.website.repository.ArticleRepository
import org.hibernate.Hibernate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ArticleServiceImpl(
    articleRepository: ArticleRepository
):
    // extends
    BaseServiceImpl<Article, Long>(
        articleRepository,
        Article::class.java
    ),

    // implements
    ArticleService
{
    @Autowired
    private lateinit var articleService: ArticleService

    @Transactional
    override fun findAll(pageable: Pageable, withTopics: Boolean): Page<Article> {
        val page = this.findAll(pageable)
        if (withTopics) {
            page.toList().forEach {
                Hibernate.initialize(it.topics)
            }
        }
        return page
    }

    @Transactional
    override fun findById(id: Long, withTopics: Boolean): Article {
        val article = this.findById(id)
        if (withTopics) {
            Hibernate.initialize(article.topics)
        }
        return article
    }

    override fun loadForUpdateById(id: Long): Article {
        return articleService.findById(id, withTopics = true)
    }

    override fun mapBeforeUpdate(updateTo: Article, updateFrom: Article): Article {
        if (updateFrom.createdAt == null) {
            updateFrom.createdAt = updateTo.createdAt
        }
        if (updateFrom.author == null) {
            updateFrom.author = updateTo.author
        }
        return updateFrom
    }
}