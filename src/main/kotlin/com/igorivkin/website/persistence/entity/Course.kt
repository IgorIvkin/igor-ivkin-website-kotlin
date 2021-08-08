package com.igorivkin.website.persistence.entity

import org.hibernate.validator.constraints.Length
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.validation.constraints.NotNull

@Entity(name = "courses")
class Course(

    @NotNull
    @Length(min = 1, max = 255)
    var title: String?,

    @OneToMany(
        mappedBy = "course",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var articles: List<CourseArticle>? = mutableListOf<CourseArticle>()

): BaseModel<Long>()