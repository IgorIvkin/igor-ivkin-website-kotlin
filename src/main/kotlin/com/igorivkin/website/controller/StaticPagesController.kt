package com.igorivkin.website.controller

import com.igorivkin.website.view.HtmlBasicView
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class StaticPagesController {
    @GetMapping("/about")
    fun renderAboutPage(model: Model): String {
        val view = HtmlBasicView(model)
        return view
            .setTitle("О сайте")
            .setMainTemplate("main-template")
            .setContent("static-pages/about :: content")
            .render()
    }
}