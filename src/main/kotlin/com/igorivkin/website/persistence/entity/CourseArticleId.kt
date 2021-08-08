package com.igorivkin.website.persistence.entity

import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class CourseArticleId(

    @Column(name = "course_id")
    val courseId: Long?,

    @Column(name = "article_id")
    val articleId: Long?

) : Serializable {

    override fun hashCode(): Int {
        return Objects.hash(courseId, articleId)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CourseArticleId

        if (courseId != other.courseId) return false
        if (articleId != other.articleId) return false

        return true
    }
}