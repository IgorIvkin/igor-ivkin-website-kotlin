package com.igorivkin.website.persistence.specification

import com.igorivkin.website.persistence.entity.Article
import org.springframework.data.jpa.domain.Specification
import java.util.*

class ArticleSpecification {

    companion object {
        fun titleContains(title: String): Specification<Article?> {
            return Specification<Article?> { root, query, cb ->
                cb.like(
                    cb.lower(root.get("title")),
                    "%" + title.lowercase(Locale.getDefault()) + "%"
                )
            }
        }
    }

}