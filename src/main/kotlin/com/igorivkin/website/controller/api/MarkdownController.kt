package com.igorivkin.website.controller.api

import com.igorivkin.website.dto.MarkdownDto
import com.igorivkin.website.service.MarkdownParserService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MarkdownController(
    private val markdownParserService: MarkdownParserService
) {
    @PostMapping(
        value = ["/api/markdown/"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun generateHtmlFromMarkDown(@RequestBody body: MarkdownDto): ResponseEntity<MarkdownDto> {
        val response = MarkdownDto(content = markdownParserService.parse(body.content))
        return ResponseEntity.ok(response)
    }
}