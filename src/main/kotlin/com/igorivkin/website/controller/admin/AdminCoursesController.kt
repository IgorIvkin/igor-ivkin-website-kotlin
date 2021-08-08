package com.igorivkin.website.controller.admin

import com.igorivkin.website.controller.dto.course.CourseCreateRequest
import com.igorivkin.website.controller.dto.course.CourseUpdateRequest
import com.igorivkin.website.response.BasicResponse
import com.igorivkin.website.response.StatusCode
import com.igorivkin.website.response.SuccessfullyModifiedResponse
import com.igorivkin.website.service.CourseService
import com.igorivkin.website.view.HtmlBasicView
import com.igorivkin.website.view.HtmlView
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
class AdminCoursesController(
    private val courseService: CourseService
) {

    private val log = LoggerFactory.getLogger(AdminCoursesController::class.java)

    @GetMapping("/admin/courses/")
    fun renderMainPage(model: Model): String {
        model.addAttribute("courses", courseService.findAll())
        val view = HtmlBasicView(model)
        return renderAdminCoursePage(
            view
                .setTitle("Администраторский интерфейс - Курсы")
                .setMainTemplate("admin/main-template")
                .setContent("admin/courses/main-page :: content")
        );
    }

    @GetMapping("/admin/courses/add")
    fun renderAddPage(model: Model): String {
        val view = HtmlBasicView(model)
        return renderAdminCoursePage(
            view
                .setTitle("Администраторский интерфейс - Добавить курс")
                .setMainTemplate("admin/main-template")
                .setContent("admin/courses/add-page :: content")
        );
    }

    @RequestMapping(
        value = ["/admin/courses/do-add/"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    )
    fun create(@Valid request: CourseCreateRequest): String {
        log.info("Create new course: {}", request)
        val courseId = courseService.create(request).id
        log.info("Created new course: {}", courseId)
        return "redirect:/admin/courses/"
    }

    @GetMapping("/admin/courses/edit/{courseId}")
    fun renderEditPage(model: Model, @PathVariable courseId: Long): String {
        model.addAttribute("course", courseService.findById(courseId))
        val view = HtmlBasicView(model)
        return renderAdminCoursePage(
            view
                .setTitle("Администраторский интерфейс - Изменить курс")
                .setMainTemplate("admin/main-template")
                .setJavascriptData("admin/courses/edit-page :: javascript-data")
                .setContent("admin/courses/edit-page :: content")
        );
    }

    @RequestMapping(
        value = ["/admin/courses/do-edit/"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun update(@RequestBody @Valid request: CourseUpdateRequest): ResponseEntity<BasicResponse> {
        log.info("Update course: {}", request)
        val courseId = request.id
        val updatedCourseId = courseService.update(courseId, request)
        log.info("Updated course: {}", updatedCourseId)
        return successfullyUpdatedCourseResponse(courseId)
    }

    private fun renderAdminCoursePage(view: HtmlView): String {
        return view
            .addJs("/js/components/article-autocomplete.js")
            .addJs("/js/infrastructure/admin/course.js")
            .render()
    }

    private fun successfullyCreatedCourseResponse(id: Long): ResponseEntity<BasicResponse> {
        return ResponseEntity.ok(
            SuccessfullyModifiedResponse(id, StatusCode.ENTITY_SUCCESSFULLY_CREATED)
        )
    }

    private fun successfullyUpdatedCourseResponse(id: Long): ResponseEntity<BasicResponse> {
        return ResponseEntity.ok(
            SuccessfullyModifiedResponse(id, StatusCode.ENTITY_SUCCESSFULLY_UPDATED)
        )
    }
}