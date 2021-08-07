package com.igorivkin.website.persistence.repository

import com.igorivkin.website.persistence.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository: JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
    fun findAllByTitleContainingIgnoreCase(title: String): List<Article>;
}