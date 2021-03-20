package com.igorivkin.website.controller.admin

import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.model.Article
import com.igorivkin.website.service.ArticleService
import com.igorivkin.website.view.HtmlBasicView
import com.igorivkin.website.view.HtmlView
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import kotlin.streams.toList

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
        val page: Page<Article> = articleService.findAll(producePageableFromPageNumber(pageNumber))
        model.addAttribute("articles", articleService.toListOfDto(page.toList()))
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

    @RequestMapping(
        value = ["/admin/articles/do-add/"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    )
    fun processDoAddArticle(model: Model, articleDto: ArticleDto): String {
        log.info("{}", articleDto)
        return "redirect:/admin/articles/"
    }

    private fun renderAdminArticlePage(view: HtmlView): String {
        return view
            .addJs("/js/components/topic-autocomplete.js")
            .render()
    }

    private fun producePageableFromPageNumber(pageNumber: Int?): Pageable {
        return if (pageNumber != null) {
            PageRequest.of(pageNumber, articlesPerPage)
        } else {
            PageRequest.of(0, articlesPerPage);
        }
    }
}