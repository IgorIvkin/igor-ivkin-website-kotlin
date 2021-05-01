package com.igorivkin.website.model

import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "courses")
class Course(

    @NotNull
    @Length(min = 2, max = 255)
    var title: String?,

    var description: String?,

    @ManyToMany(cascade = [CascadeType.REFRESH, CascadeType.REMOVE], fetch = FetchType.LAZY)
    @JoinTable(
        name = "courses_articles",
        joinColumns = [JoinColumn(name = "id_course", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "id_article", referencedColumnName = "id")]
    )
    var articles: List<Article>? = mutableListOf<Article>()

): BaseModel<Long>()