package com.igorivkin.website.service.mapper

import com.igorivkin.website.converter.ArticleConverter
import com.igorivkin.website.converter.ArticleConverterWithTopics
import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.model.Article
import org.mapstruct.factory.Mappers

class ArticleMapper {
    companion object {
        private val converter: ArticleConverter = Mappers.getMapper(ArticleConverter::class.java)
        private val converterWithTopics: ArticleConverterWithTopics = Mappers.getMapper(ArticleConverterWithTopics::class.java)

        fun toDto(article: Article, withTopics: Boolean = false): ArticleDto {
            return if (withTopics) {
                converterWithTopics.toDto(article)
            } else {
                converter.toDto(article)
            }
        }

        fun toListOfDto(articles: List<Article>): List<ArticleDto> {
            return articles.map {
                converter.toDto(it)
            }.toList();
        }

        fun toModel(topicDto: ArticleDto, withTopics: Boolean = false): Article {
            return if (withTopics) {
                converterWithTopics.toModel(topicDto)
            } else {
                converter.toModel(topicDto)
            }
        }

    }
}