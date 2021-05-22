package com.igorivkin.website.dto

import java.util.*

data class CourseDto(
    var id: Long? = null,
    var title: String,
    var description: String?,
    var orderedArticles: LongArray?,
    var articles: List<ArticleDto>? = mutableListOf<ArticleDto>()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CourseDto

        if (other.hashCode() != hashCode()) return false
        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (!orderedArticles.contentEquals(other.orderedArticles)) return false
        if (articles != other.articles) return false

        return true
    }

    override fun hashCode(): Int {
        return Objects.hash(id, title, description, orderedArticles)
    }
}