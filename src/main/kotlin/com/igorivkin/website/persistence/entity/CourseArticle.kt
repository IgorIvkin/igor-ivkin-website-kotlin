package com.igorivkin.website.persistence.entity

import com.sun.istack.NotNull
import javax.persistence.*

@Entity(name = "course_article")
class CourseArticle(

    @EmbeddedId
    var id: CourseArticleId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    var course: Course,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("articleId")
    var article: Article,

    @NotNull
    @Column(name = "\"order\"")
    var order: Int
) {
    init {
        this.id = CourseArticleId(courseId = course.id, articleId = article.id)
    }
}