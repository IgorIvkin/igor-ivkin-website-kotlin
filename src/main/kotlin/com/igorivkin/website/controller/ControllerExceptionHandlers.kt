package com.igorivkin.website.controller

import com.igorivkin.website.exception.EntityDoesNotExistException
import com.igorivkin.website.view.HtmlBasicView
import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ControllerExceptionHandlers {

    @ExceptionHandler(EntityDoesNotExistException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleEntityDoesNotExist(ex: EntityDoesNotExistException, model: Model): String {
        val view = HtmlBasicView(model)
        return view
            .setTitle("404 - не найдено!")
            .setMainTemplate("main-template")
            .setContent("errors/not-found :: content")
            .render()
    }
}