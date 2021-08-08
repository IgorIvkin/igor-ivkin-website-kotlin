package com.igorivkin.website.controller.api

import com.igorivkin.website.controller.dto.markdown.MarkdownRequest
import com.igorivkin.website.controller.dto.markdown.MarkdownResponse
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
    fun generateHtmlFromMarkDown(@RequestBody body: MarkdownRequest): ResponseEntity<MarkdownResponse> {
        val response = MarkdownResponse(content = markdownParserService.parse(body.content))
        return ResponseEntity.ok(response)
    }
}