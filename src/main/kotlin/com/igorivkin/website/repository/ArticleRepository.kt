package com.igorivkin.website.repository

import com.igorivkin.website.model.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository: JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
}