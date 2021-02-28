package com.igorivkin.website.controller

import com.igorivkin.website.model.User
import com.igorivkin.website.model.UserRole
import com.igorivkin.website.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainPageController {

    @GetMapping("/")
    fun renderMainPage(): String {
        return "main-page/main"
    }
}