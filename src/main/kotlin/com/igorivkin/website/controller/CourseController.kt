package com.igorivkin.website.controller

import com.igorivkin.website.controller.dto.course.CourseArticleGetResponse
import com.igorivkin.website.service.CourseService
import com.igorivkin.website.view.HtmlBasicView
import com.igorivkin.website.view.HtmlView
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class CourseController(
    private val courseService: CourseService
) {

    @GetMapping("/courses")
    fun renderAllCoursesPage(model: Model): String {
        model.addAttribute("courses", courseService.findAll())
        val view = HtmlBasicView(model)
        return renderCoursePage(
            view
                .setTitle("Список курсов")
                .setMainTemplate("main-template")
                .setContent("courses/list-page  :: content")
        );
    }

    @GetMapping("/courses/{courseId}")
    fun renderCoursePage(model: Model, @PathVariable courseId: Long): String {
        val course = courseService.findById(courseId)
        model.addAttribute("course", courseService.findById(courseId))
        model.addAttribute("orderedArticles", orderArticlesByOrder(course.articles))
        val view = HtmlBasicView(model)
        return renderCoursePage(
            view
                .setTitle("Курс ${course.title}")
                .setMainTemplate("main-template")
                .setContent("courses/main-page :: content")
        );
    }

    private fun orderArticlesByOrder(articles: List<CourseArticleGetResponse>): List<CourseArticleGetResponse> {
        return articles.sortedBy { it.order }
    }

    private fun renderCoursePage(view: HtmlView): String {
        return view
            .addJs("/js/components/pagination.js")
            .render()
    }
}