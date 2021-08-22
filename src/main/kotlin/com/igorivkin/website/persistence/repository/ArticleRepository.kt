package com.igorivkin.website.persistence.repository

import com.igorivkin.website.persistence.entity.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository: JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
    fun findAllByTitleContainingIgnoreCase(title: String): List<Article>;

    fun findAllByTopicsId(topicId: Long, pageable: Pageable): Page<Article>;

    @Query(
        nativeQuery = true,
        value = "select * from articles "
                + "where to_tsvector('russian', title || ' ' || \"content\") @@ to_tsquery(:query) "
                + "order by id desc limit 20"
    )
    fun findAllBySearchQuery(query: String): List<Article>;
}