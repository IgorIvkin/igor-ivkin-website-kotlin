package com.igorivkin.website.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.validator.constraints.Length
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "users")
class User(

    // id field taken from BaseModel-entity here

    @NotNull
    @Length(min = 1, max = 255)
    var username: String,

    @NotNull
    @JsonIgnore
    var password: String,

    @NotNull
    @Length(min = 1, max = 255)
    var title: String,

    var email: String? = null,
    var idOauth: String? = null,

    @CreationTimestamp
    var createdAt: Instant? = null,

    @UpdateTimestamp
    var updatedAt: Instant? = null,

    var enabled: Boolean = true,

    @Enumerated(EnumType.ORDINAL)
    var role: UserRole,

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var articles: List<Article>? = mutableListOf<Article>()

): BaseModel<Long>()
