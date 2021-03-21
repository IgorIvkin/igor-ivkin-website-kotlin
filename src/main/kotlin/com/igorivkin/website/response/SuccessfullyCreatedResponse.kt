package com.igorivkin.website.response

class SuccessfullyCreatedResponse<IdT>(id: IdT, statusCode: Int, message: String?):
    BasicResponse(statusCode, message) {

    constructor(id: IdT, statusCode: Int) : this(id, statusCode, "")
}