package com.igorivkin.website.controller.admin

import com.igorivkin.website.service.CourseService
import com.igorivkin.website.view.HtmlBasicView
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AdminCoursesController(
    private val courseService: CourseService
) {
    private val log = LoggerFactory.getLogger(AdminCoursesController::class.java)

    @GetMapping("/admin/courses/")
    fun renderMainPage(model: Model): String {
        model.addAttribute("courses", courseService.findAll())
        val view = HtmlBasicView(model)
        return view
            .setTitle("Администраторский интерфейс - Список всех курсов")
            .setMainTemplate("admin/main-template")
            .setContent("admin/courses/main-page :: content")
            .render();
    }

    @GetMapping("/admin/courses/add/")
    fun renderAddPage(model: Model): String {
        val view = HtmlBasicView(model)
        return view
            .setTitle("Администраторский интерфейс - Создать новый курс")
            .setMainTemplate("admin/main-template")
            .setContent("admin/courses/add-page :: content")
            .render()
    }
}