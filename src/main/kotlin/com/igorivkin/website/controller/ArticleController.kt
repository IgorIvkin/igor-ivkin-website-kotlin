package com.igorivkin.website.controller

import com.igorivkin.website.service.ArticleService
import com.igorivkin.website.service.MarkdownParserService
import com.igorivkin.website.service.mapper.ArticleMapper
import com.igorivkin.website.view.HtmlBasicView
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class ArticleController(
    private val articleService: ArticleService,
    private val markdownParserService: MarkdownParserService
) {

    @GetMapping("/articles/{articleId}")
    fun renderArticlePage(@PathVariable articleId: Long, model: Model): String {
        val article = ArticleMapper.toDto(
            articleService.findById(articleId, withTopics = true),
            withTopics = true
        )
        article.content = markdownParserService.parse(article.content)

        model.addAttribute("article", article)
        val view = HtmlBasicView(model)
        return view
            .setTitle(article.title + " — Статья на сайте igorivkin.com")
            .setMainTemplate("main-template")
            .setContent("articles/main-page :: content")
            .render()
    }

}