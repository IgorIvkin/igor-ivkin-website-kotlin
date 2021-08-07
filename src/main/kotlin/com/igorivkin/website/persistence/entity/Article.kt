package com.igorivkin.website.persistence.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.validator.constraints.Length
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "articles")
class Article(

    // id field taken from BaseModel-entity here

    @NotNull
    @Length(min = 2, max = 255)
    var title: String?,

    @NotNull
    @Column(name = "content", columnDefinition = "text")
    var content: String?,

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    var author: User?,

    @CreationTimestamp
    var createdAt: Instant?,

    @UpdateTimestamp
    var updatedAt: Instant?,

    @ManyToMany(
        cascade = [CascadeType.REFRESH, CascadeType.REMOVE],
        fetch = FetchType.LAZY
    )
    @JoinTable(
        name = "articles_topics",
        joinColumns = [JoinColumn(name = "id_article", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "id_topic", referencedColumnName = "id")]
    )
    var topics: List<Topic>? = mutableListOf<Topic>()

): BaseModel<Long>()