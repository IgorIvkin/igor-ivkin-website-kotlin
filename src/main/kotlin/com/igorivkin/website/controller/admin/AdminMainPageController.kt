package com.igorivkin.website.controller.admin

import com.igorivkin.website.view.HtmlBasicView
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AdminMainPageController {

    @GetMapping("/admin/")
    fun renderMainPage(model: Model): String {
        val view = HtmlBasicView(model)
        return view
            .setTitle("Администраторский интерфейс - Главная страница")
            .setMainTemplate("admin/main-template")
            .setContent("admin/main-page :: content")
            .render();
    }

}