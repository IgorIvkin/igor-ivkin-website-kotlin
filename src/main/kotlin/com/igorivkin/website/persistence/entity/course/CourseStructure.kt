package com.igorivkin.website.persistence.entity.course

import java.io.Serializable

data class CourseStructure(
    var items: List<CourseItem> = listOf()
): Serializable