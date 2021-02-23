package com.igorivkin.website.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.validator.constraints.Length
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @NotNull
    @Length(min = 1, max = 255)
    var username: String?,

    @NotNull
    @JsonIgnore
    var password: String?,

    @NotNull
    @Length(min = 1, max = 255)
    var title: String?,

    var email: String?,
    var idOauth: String?,

    @CreationTimestamp
    var createdAt: Instant?,

    @UpdateTimestamp
    var updatedAt: Instant?,

    var enabled: Boolean? = true,

    @Enumerated(EnumType.ORDINAL)
    var role: UserRole?
)
