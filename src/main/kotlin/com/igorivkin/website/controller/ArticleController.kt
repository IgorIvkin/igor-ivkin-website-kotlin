package com.igorivkin.website.controller

import com.igorivkin.website.service.ArticleService
import com.igorivkin.website.service.MarkdownParserService
import com.igorivkin.website.view.HtmlBasicView
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

const val DEFAULT_PAGE: Int = 0
const val ARTICLES_PER_PAGE: Int = 10

@Controller
class ArticleController(
    private val articleService: ArticleService,
    private val markdownParserService: MarkdownParserService
) {

    @GetMapping("/articles")
    fun renderMainPage(model: Model): String {
        val articles = articleService.findAll(DEFAULT_PAGE, ARTICLES_PER_PAGE)
        model.addAttribute("articles", articles)
        model.addAttribute("totalFound", articles.totalElements)
        model.addAttribute("totalPages", articles.totalPages)
        val view = HtmlBasicView(model)
        return view
            .setTitle("Список статей на сайте")
            .setMainTemplate("main-template")
            .setContent("articles/list-page :: content")
            .render()
    }

    @GetMapping("/articles/{articleId}")
    fun renderArticlePage(@PathVariable articleId: Long, model: Model): String {
        val article = articleService.findById(articleId)
        val articleModified = article.copy(content = markdownParserService.parse(article.content))

        model.addAttribute("article", articleModified)
        val view = HtmlBasicView(model)
        return view
            .setTitle(article.title + " — Статья на сайте igorivkin.com")
            .setMainTemplate("main-template")
            .setContent("articles/main-page :: content")
            .render()
    }

}