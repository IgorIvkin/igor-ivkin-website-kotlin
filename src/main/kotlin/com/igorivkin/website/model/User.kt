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

    var email: String?,
    var idOauth: String?,

    @CreationTimestamp
    var createdAt: Instant?,

    @UpdateTimestamp
    var updatedAt: Instant?,

    var enabled: Boolean? = true,

    @Enumerated(EnumType.ORDINAL)
    var role: UserRole,

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var articles: List<Article>?

): BaseModel<Long>()
