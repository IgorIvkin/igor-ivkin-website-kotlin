package com.igorivkin.website.controller.dto.topic

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class TopicCreateRequest(
    @NotBlank
    @Size(max = 255)
    val title: String
)