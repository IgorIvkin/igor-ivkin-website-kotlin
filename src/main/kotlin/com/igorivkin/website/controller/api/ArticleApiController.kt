package com.igorivkin.website.controller.api

import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.service.ArticleService
import com.igorivkin.website.service.mapper.ArticleMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ArticleApiController(
    private val articleService: ArticleService
) {
    @GetMapping("/api/articles/")
    fun findByTitle(@RequestParam title: String): ResponseEntity<List<ArticleDto>> {
        val articles = ArticleMapper.toListOfDto(articleService.findByTitle(title))
        return if(articles.isNotEmpty()) {
            ResponseEntity.ok(articles)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }

    }
}