package com.igorivkin.website.service

import com.igorivkin.website.converter.ArticleConverter
import com.igorivkin.website.converter.ArticleConverterWithTopics
import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.model.Article
import com.igorivkin.website.repository.ArticleRepository
import org.hibernate.Hibernate
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import javax.transaction.Transactional

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
    @Autowired
    private lateinit var articleService: ArticleService

    private val converter: ArticleConverter = Mappers.getMapper(ArticleConverter::class.java)
    private val converterWithTopics: ArticleConverterWithTopics = Mappers.getMapper(ArticleConverterWithTopics::class.java)

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

    override fun toModel(dto: ArticleDto): Article {
        return converter.toModel(dto)
    }

    override fun toDto(entity: Article): ArticleDto {
        return converter.toDto(entity)
    }

    override fun fromDto(dto: ArticleDto, entity: Article): Article {
        converterWithTopics.fromDto(dto, entity)
        return entity
    }

    override fun toListOfDtoWithTopics(entityList: List<Article>): List<ArticleDto> {
        return entityList.map {
                entity -> this.toDtoWithTopics(entity)
        }.toList()
    }

    override fun toDtoWithTopics(entity: Article): ArticleDto {
        return converterWithTopics.toDto(entity)
    }
}