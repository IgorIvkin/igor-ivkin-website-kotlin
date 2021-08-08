package com.igorivkin.website.controller

import com.igorivkin.website.view.HtmlBasicView
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainPageController {

    @GetMapping("/")
    fun renderMainPage(model: Model): String {
        val view = HtmlBasicView(model)
        return view
            .setTitle("Добро пожаловать на сайт лаборатории Игоря Ивкина")
            .setMainTemplate("main-template")
            .setContent("main-page/main-page :: content")
            .render()
    }
}