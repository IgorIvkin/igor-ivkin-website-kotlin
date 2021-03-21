package com.igorivkin.website.response

abstract class BasicResponse(val statusCode: Int, val message: String = "") {
}