package com.igorivkin.website.service.mapper

import com.igorivkin.website.controller.dto.IdValue
import com.igorivkin.website.controller.dto.article.ArticleCreateRequest
import com.igorivkin.website.controller.dto.article.ArticleGetResponse
import com.igorivkin.website.controller.dto.article.ArticleGetSimplifiedResponse
import com.igorivkin.website.controller.dto.article.ArticleUpdateRequest
import com.igorivkin.website.persistence.entity.Article
import com.igorivkin.website.persistence.entity.Topic
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.Mappings

@Mapper(
    componentModel = "spring",
    uses = [UserMapper::class, TopicMapper::class]
)
interface ArticleMapper {
    @Mappings(
        Mapping(source = "id", target = "id"),
        Mapping(source = "title", target = "title"),
        Mapping(source = "content", target = "content"),
        Mapping(source = "author", target = "author"),
        Mapping(source = "topics", target = "topics")
    )
    fun toDto(article: Article): ArticleGetResponse

    fun toDto(articles: List<Article>): List<ArticleGetResponse>

    @Mappings(
        Mapping(source = "id", target = "id"),
        Mapping(source = "title", target = "title")
    )
    fun toSimplifiedDto(article: Article): ArticleGetSimplifiedResponse

    fun toSimplifiedDto(articles: List<Article>): List<ArticleGetSimplifiedResponse>

    @Mappings(
        Mapping(source = "title", target = "title"),
        Mapping(source = "content", target = "content"),
        Mapping(source = "author.id", target = "author.id"),
        Mapping(source = "topics", target = "topics")
    )
    fun toModel(request: ArticleCreateRequest): Article

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(source = "title", target = "title"),
        Mapping(source = "content", target = "content"),
        Mapping(source = "author.id", target = "author.id"),
        Mapping(source = "topics", target = "topics")
    )
    fun update(request: ArticleUpdateRequest, @MappingTarget article: Article)

    @Mappings(
        Mapping(source = "id", target = "id")
    )
    fun idValueToTopic(idValue: IdValue<Long>): Topic

    fun idValueToTopic(idValues: List<IdValue<Long>>): List<Topic>

}