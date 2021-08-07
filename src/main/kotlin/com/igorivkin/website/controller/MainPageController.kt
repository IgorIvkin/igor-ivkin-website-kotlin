package com.igorivkin.website.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainPageController {

    @GetMapping("/")
    fun renderMainPage(): String {
        return "main-page/main"
    }
}