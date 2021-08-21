package com.igorivkin.website.controller

import com.igorivkin.website.exception.EntityDoesNotExistException
import com.igorivkin.website.service.ArticleService
import com.igorivkin.website.service.MarkdownParserService
import com.igorivkin.website.service.TopicService
import com.igorivkin.website.view.HtmlBasicView
import com.igorivkin.website.view.HtmlView
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
    private val topicService: TopicService,
    private val markdownParserService: MarkdownParserService
) {

    @GetMapping("/articles")
    fun renderMainPage(model: Model): String {
        val articles = articleService.findAll(DEFAULT_PAGE, ARTICLES_PER_PAGE)
        model.addAttribute("articles", articles.toList())
        model.addAttribute("totalFound", articles.totalElements)
        model.addAttribute("totalPages", articles.totalPages)
        model.addAttribute("currentPage", 1)
        val view = HtmlBasicView(model)
        return renderArticlePage(
            view
                .setTitle("Список статей на сайте")
                .setMainTemplate("main-template")
                .setJavascriptData("articles/list-page :: javascript-data")
                .setContent("articles/list-page :: content")
        );
    }

    @GetMapping("/articles/page/{pageId}")
    fun renderPagedArticlePage(model: Model, @PathVariable pageId: Int): String {
        val articles = articleService.findAll(pageId - 1, ARTICLES_PER_PAGE)
        if (articles.toList().size == 0) {
            throw EntityDoesNotExistException.noArticlesFoundByCriteria()
        }
        model.addAttribute("articles", articles.toList())
        model.addAttribute("totalFound", articles.totalElements)
        model.addAttribute("totalPages", articles.totalPages)
        model.addAttribute("currentPage", pageId);
        val view = HtmlBasicView(model)
        return renderArticlePage(
            view
                .setTitle("Список статей на сайте")
                .setMainTemplate("main-template")
                .setJavascriptData("articles/list-page :: javascript-data")
                .setContent("articles/list-page :: content")
        );
    }

    @GetMapping("/articles/topics/{topicId}/page/{pageId}")
    fun renderPageArticleByTopicPage(model: Model,
                                     @PathVariable pageId: Int,
                                     @PathVariable topicId: Long): String {
        val articles = articleService.findAllByTopicId(
            topicId = topicId,
            pageNum = pageId - 1,
            articlesPerPage = ARTICLES_PER_PAGE
        )
        if (articles.toList().size == 0) {
            throw EntityDoesNotExistException.noArticlesFoundByCriteria()
        }
        val topic = topicService.findById(topicId)
        model.addAttribute("topic", topic)
        model.addAttribute("articles", articles.toList())
        model.addAttribute("totalFound", articles.totalElements)
        model.addAttribute("totalPages", articles.totalPages)
        model.addAttribute("currentPage", pageId);
        model.addAttribute("basicPaginationUrl", "/articles/topics/$topicId/page/")
        val view = HtmlBasicView(model)
        return renderArticlePage(
            view
                .setTitle("Список статей на сайте по теме ${topic.title}")
                .setMainTemplate("main-template")
                .setJavascriptData("articles/list-page :: javascript-data")
                .setContent("articles/list-page :: content")
        );
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

    private fun renderArticlePage(view: HtmlView): String {
        return view
            .addJs("/js/components/pagination.js")
            .render()
    }

}