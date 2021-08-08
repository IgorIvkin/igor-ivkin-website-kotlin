package com.igorivkin.website.service.mapper

import com.igorivkin.website.controller.dto.course.*
import com.igorivkin.website.persistence.entity.Course
import com.igorivkin.website.persistence.entity.CourseArticle
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface CourseMapper {
    @Mappings(
        Mapping(source = "id", target = "id"),
        Mapping(source = "title", target = "title"),
        Mapping(source = "articles", target = "articles")
    )
    fun toDto(course: Course): CourseGetResponse

    @Mappings(
        Mapping(source = "course.id", target = "courseId"),
        Mapping(source = "article.id", target = "articleId"),
        Mapping(source = "article.title", target = "articleTitle"),
        Mapping(source = "order", target = "order")
    )
    fun toDto(courseArticle: CourseArticle): CourseArticleGetResponse

    fun toDto(courseArticles: List<CourseArticle>): List<CourseArticleGetResponse>

    @Mappings(
        Mapping(source = "id", target = "id"),
        Mapping(source = "title", target = "title")
    )
    fun toSimplifiedDto(course: Course): CourseGetSimplifiedResponse

    fun toSimplifiedDto(course: List<Course>): List<CourseGetSimplifiedResponse>

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "articles", ignore = true),
        Mapping(source = "title", target = "title"),
    )
    fun toModel(course: CourseCreateRequest): Course

    @Mappings(
        Mapping(source = "title", target = "title"),
        Mapping(source = "articles", target = "articles"),
    )
    fun update(request: CourseUpdateRequest, @MappingTarget course: Course)

    @Mappings(
        Mapping(source = "courseId", target = "id.courseId"),
        Mapping(source = "courseId", target = "course.id"),
        Mapping(source = "articleId", target = "article.id"),
        Mapping(source = "articleId", target = "id.articleId"),
        Mapping(source = "order", target = "order")
    )
    fun toModel(courseArticle: CourseArticleUpdate): CourseArticle

    fun toModel(courseArticle: List<CourseArticleUpdate>): List<CourseArticle>
}