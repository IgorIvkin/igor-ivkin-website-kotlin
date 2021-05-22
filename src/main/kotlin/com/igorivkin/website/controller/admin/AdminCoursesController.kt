package com.igorivkin.website.controller.admin

import com.igorivkin.website.dto.CourseDto
import com.igorivkin.website.response.BasicResponse
import com.igorivkin.website.response.StatusCode
import com.igorivkin.website.response.SuccessfullyModifiedResponse
import com.igorivkin.website.service.CourseService
import com.igorivkin.website.service.mapper.CourseMapper
import com.igorivkin.website.view.HtmlBasicView
import com.igorivkin.website.view.HtmlView
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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
        return renderAdminCoursesPage(
            view
                .setTitle("Администраторский интерфейс - Создать новый курс")
                .setMainTemplate("admin/main-template")
                .setContent("admin/courses/add-page :: content")
        )
    }

    @RequestMapping(
        value = ["/admin/courses/do-add/"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun add(
        model: Model,
        @RequestBody courseDto: CourseDto
    ): ResponseEntity<BasicResponse> {
        log.info("Received new course: {}", courseDto)
        val createdCourse = courseService.create(CourseMapper.toModel(courseDto = courseDto))
        val createdCourseId = createdCourse.id
        return if (createdCourseId != null) {
            successfullyCreatedCourseResponse(createdCourseId)
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private fun renderAdminCoursesPage(view: HtmlView): String {
        return view
            .addJs("/js/components/article-autocomplete.js")
            .render()
    }

    private fun successfullyCreatedCourseResponse(id: Long): ResponseEntity<BasicResponse> {
        return ResponseEntity.ok(
            SuccessfullyModifiedResponse(
                id,
                StatusCode.ENTITY_SUCCESSFULLY_CREATED
            )
        )
    }
}