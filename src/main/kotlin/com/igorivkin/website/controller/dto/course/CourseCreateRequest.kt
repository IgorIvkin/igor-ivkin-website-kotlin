package com.igorivkin.website.controller.dto.course

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CourseCreateRequest(
    @NotBlank
    @Size(max = 255)
    val title: String,
    val description: String
)