package com.igorivkin.website.controller.admin

import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.service.mapper.ArticleMapper
import com.igorivkin.website.model.Article
import com.igorivkin.website.response.BasicResponse
import com.igorivkin.website.response.StatusCode
import com.igorivkin.website.response.SuccessfullyModifiedResponse
import com.igorivkin.website.service.ArticleService
import com.igorivkin.website.view.HtmlBasicView
import com.igorivkin.website.view.HtmlView
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@Controller
class AdminArticlesController(
    private val articleService: ArticleService
) {

    private val log = LoggerFactory.getLogger(AdminArticlesController::class.java)
    private val articlesPerPage: Int = 10

    @GetMapping("/admin/articles/", "/admin/articles/page/{pageNumber}")
    fun renderMainPage(
        model: Model,
        @PathVariable(required = false) pageNumber: Int?
    ): String {
        val page: Page<Article> = articleService.findAll(pageable = producePageableFromPageNumber(pageNumber))
        model.addAttribute("articles", ArticleMapper.toListOfDto(page.toList()))
        model.addAttribute("pageCount", page.totalPages)
        val view = HtmlBasicView(model)
        return renderAdminArticlePage(
            view
                .setTitle("Администраторский интерфейс - Статьи")
                .setMainTemplate("admin/main-template")
                .setContent("admin/articles/main-page :: content")
        );
    }

    @GetMapping("/admin/articles/add/")
    fun renderAddPage(model: Model): String {
        val view = HtmlBasicView(model)
        return renderAdminArticlePage(
            view
                .setTitle("Администраторский интерфейс - Статьи")
                .setMainTemplate("admin/main-template")
                .setContent("admin/articles/add-page :: content")
        );
    }

    @GetMapping("/admin/articles/edit/{articleId}")
    fun renderEditPage(
        model: Model,
        @PathVariable articleId: Long
    ): String {
        model.addAttribute(
            "article",
            ArticleMapper.toDto(
                articleService.findById(id = articleId, withTopics = true),
                withTopics = true
            )
        )
        val view = HtmlBasicView(model)
        return renderAdminArticlePage(
            view
                .setTitle("Администраторский интерфейс - Статьи")
                .setMainTemplate("admin/main-template")
                .setJavascriptData("admin/articles/edit-page :: javascript-data")
                .setContent("admin/articles/edit-page :: content")
        );
    }

    @RequestMapping(
        value = ["/admin/articles/do-add/"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun add(
        model: Model,
        @RequestBody articleDto: ArticleDto
    ): ResponseEntity<BasicResponse> {
        log.info("Received new article: {}", articleDto)
        val createdArticle: Article = articleService.create(ArticleMapper.toModel(articleDto, withTopics = true))
        val createdArticleId = createdArticle.id
        log.info("Created new article: {}", createdArticle)
        return if (createdArticleId != null) {
            successfullyCreatedArticleResponse(createdArticleId)
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @RequestMapping(
        value = ["/admin/articles/do-edit/"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun edit(
        model: Model,
        @RequestBody articleDto: ArticleDto
    ): ResponseEntity<BasicResponse> {
        log.info("Received new article: {}", articleDto)
        val articleId = articleDto.id
        if (articleId == null) {
            throw IllegalArgumentException("Cannot update article, empty ID is provided")
        } else {
            val updatedArticle: Article = articleService.update(
                articleId,
                ArticleMapper.toModel(articleDto, withTopics = true)
            )
            log.info("Updated an article: {}", updatedArticle)
            return successfullyUpdatedArticleResponse(articleId)
        }
    }

    private fun renderAdminArticlePage(view: HtmlView): String {
        return view
            .addJs("/js/components/topic-autocomplete.js")
            .addJs("/js/components/article-preview.js")
            .addJs("/js/infrastructure/admin/article.js")
            .render()
    }

    private fun producePageableFromPageNumber(pageNumber: Int?): Pageable {
        return if (pageNumber != null) {
            PageRequest.of(pageNumber, articlesPerPage)
        } else {
            PageRequest.of(0, articlesPerPage);
        }
    }

    private fun successfullyCreatedArticleResponse(id: Long): ResponseEntity<BasicResponse> {
        return ResponseEntity.ok(
            SuccessfullyModifiedResponse(
                id,
                StatusCode.ENTITY_SUCCESSFULLY_CREATED
            )
        )
    }

    private fun successfullyUpdatedArticleResponse(id: Long): ResponseEntity<BasicResponse> {
        return ResponseEntity.ok(
            SuccessfullyModifiedResponse(
                id,
                StatusCode.ENTITY_SUCCESSFULLY_UPDATED
            )
        )
    }
}