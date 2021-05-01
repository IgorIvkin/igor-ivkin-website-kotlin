package com.igorivkin.website.repository.specification

import com.igorivkin.website.model.Article
import org.springframework.data.jpa.domain.Specification

class ArticleSpecifications {

    companion object {
        fun titleContains(title: String): Specification<Article?> {
            return Specification<Article?> { root, query, cb ->
                cb.like(
                    cb.lower(root.get("title")),
                    "%" + title.toLowerCase() + "%"
                )
            }
        }
    }

}