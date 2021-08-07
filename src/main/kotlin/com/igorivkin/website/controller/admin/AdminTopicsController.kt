package com.igorivkin.website.controller.admin

import com.igorivkin.website.controller.dto.topic.TopicCreateRequest
import com.igorivkin.website.controller.dto.topic.TopicUpdateRequest
import com.igorivkin.website.dto.TopicDto
import com.igorivkin.website.service.TopicService
import com.igorivkin.website.view.HtmlBasicView
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
class AdminTopicsController(
    private val topicService: TopicService
) {

    @GetMapping("/admin/topics/")
    fun renderMainPage(model: Model): String {
        model.addAttribute("topics", topicService.findAll())
        val view = HtmlBasicView(model)
        return view
            .setTitle("Администраторский интерфейс - Темы статей")
            .setMainTemplate("admin/main-template")
            .setContent("admin/topics/main-page :: content")
            .render();
    }

    @GetMapping("/admin/topics/add/")
    fun renderAddPage(model: Model): String {
        val view = HtmlBasicView(model)
        return view
            .setTitle("Администраторский интерфейс - Добавить тему")
            .setMainTemplate("admin/main-template")
            .setContent("admin/topics/add-page :: content")
            .render();
    }

    @RequestMapping(
        value = ["/admin/topics/do-add/"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    )
    fun add(@Valid request: TopicCreateRequest): String {
        topicService.create(request)
        return "redirect:/admin/topics/"
    }

    @GetMapping("/admin/topics/edit/{topicId}")
    fun renderEditPage(
        model: Model,
        @PathVariable topicId: Long,
        @ModelAttribute("updateStatus") updateStatus: String
    ): String {
        model.addAttribute("topic", topicService.findById(topicId))
        val view = HtmlBasicView(model)
        return view
            .setTitle("Администраторский интерфейс - Редактировать тему")
            .setMainTemplate("admin/main-template")
            .setContent("admin/topics/edit-page :: content")
            .render();
    }

    @RequestMapping(
        value = ["/admin/topics/do-edit/"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    )
    fun edit(
        redirectAttributes: RedirectAttributes,
        @RequestParam("id") topicId: Long,
        @Valid request: TopicUpdateRequest
    ): String {
        topicService.update(topicId, request)
        redirectAttributes.addFlashAttribute("updateStatus", "success")
        return "redirect:/admin/topics/edit/${topicId}/"
    }
}