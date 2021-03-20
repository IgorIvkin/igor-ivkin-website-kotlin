package com.igorivkin.website.controller.admin

import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.service.ArticleService
import com.igorivkin.website.view.HtmlBasicView
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class AdminArticlesController(
    private val articleService: ArticleService
) {

    private val log = LoggerFactory.getLogger(AdminArticlesController::class.java);

    @GetMapping("/admin/articles/")
    fun renderMainPage(model: Model): String {
        val view = HtmlBasicView(model)
        return view
            .setTitle("Администраторский интерфейс - Статьи")
            .setMainTemplate("admin/main-template")
            .setContent("admin/articles/main-page :: content")
            .render();
    }

    @GetMapping("/admin/articles/add/")
    fun renderAddPage(model: Model): String {
        val view = HtmlBasicView(model)
        return view
            .setTitle("Администраторский интерфейс - Статьи")
            .setMainTemplate("admin/main-template")
            .setContent("admin/articles/add-page :: content")
            .render();
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
}