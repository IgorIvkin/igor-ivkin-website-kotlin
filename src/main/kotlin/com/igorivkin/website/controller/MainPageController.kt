package com.igorivkin.website.controller

import com.igorivkin.website.service.ArticleService
import com.igorivkin.website.view.HtmlBasicView
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainPageController(
    private val articleService: ArticleService
) {

    @GetMapping("/")
    fun renderMainPage(model: Model): String {
        // Получаем 10 самых новых статей
        model.addAttribute("newestArticles", articleService.findAll(0, 10))
        val view = HtmlBasicView(model)
        return view
            .setTitle("Добро пожаловать на сайт лаборатории Игоря Ивкина")
            .setMainTemplate("main-template")
            .setContent("main-page/main-page :: content")
            .render()
    }
}