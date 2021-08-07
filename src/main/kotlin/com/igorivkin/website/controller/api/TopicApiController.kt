package com.igorivkin.website.controller.api

import com.igorivkin.website.controller.dto.topic.TopicGetResponse
import com.igorivkin.website.dto.TopicDto
import com.igorivkin.website.service.TopicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TopicApiController(
    private val topicService: TopicService
) {

    @GetMapping("/api/topics/{title}")
    fun findByTitle(@PathVariable title: String): ResponseEntity<List<TopicGetResponse>> {
        val topics = topicService.findByTitle(title)
        return if(topics.isNotEmpty()) {
            ResponseEntity.ok(topics)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }
}