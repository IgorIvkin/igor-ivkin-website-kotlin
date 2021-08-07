package com.igorivkin.website.persistence.entity

import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "topics")
class Topic(

    // id field taken from BaseModel-entity here

    @NotNull
    @Length(min = 1, max = 255)
    var title: String?,

    @ManyToMany(mappedBy = "topics", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var articles: List<Article>? = mutableListOf<Article>()

): BaseModel<Long>()