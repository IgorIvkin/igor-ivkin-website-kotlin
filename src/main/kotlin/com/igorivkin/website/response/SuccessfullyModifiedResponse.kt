package com.igorivkin.website.response

class SuccessfullyModifiedResponse<IdT>(
    val id: IdT,
    statusCode: Int,
    message: String = ""
) : BasicResponse(statusCode, message)