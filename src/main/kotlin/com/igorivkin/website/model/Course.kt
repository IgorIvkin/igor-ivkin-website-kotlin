package com.igorivkin.website.model

import com.vladmihalcea.hibernate.type.array.IntArrayType
import com.vladmihalcea.hibernate.type.array.LongArrayType
import com.vladmihalcea.hibernate.type.array.StringArrayType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "courses")
@TypeDefs(value = [
    TypeDef(name = "long-array", typeClass = LongArrayType::class),
])
class Course(

    @NotNull
    @Length(min = 2, max = 255)
    var title: String?,

    var description: String?,

    @Type(type = "long-array")
    @Column(
        name = "ordered_articles",
        columnDefinition = "int8[]"
    )
    var orderedArticles: LongArray,

    @ManyToMany(cascade = [CascadeType.REFRESH, CascadeType.REMOVE], fetch = FetchType.LAZY)
    @JoinTable(
        name = "courses_articles",
        joinColumns = [JoinColumn(name = "id_course", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "id_article", referencedColumnName = "id")]
    )
    var articles: List<Article>? = mutableListOf<Article>()

): BaseModel<Long>()