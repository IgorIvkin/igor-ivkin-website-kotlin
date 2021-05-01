package com.igorivkin.website.model.course

import java.io.Serializable

data class CourseItem(
    var idArticle: Long,
    var mandatory: Boolean = false
): Serializable