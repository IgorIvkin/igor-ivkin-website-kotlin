package com.igorivkin.website.model.course

import java.io.Serializable

data class CourseStructure(
    var items: List<CourseItem> = listOf()
): Serializable