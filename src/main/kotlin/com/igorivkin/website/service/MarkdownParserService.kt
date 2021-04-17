package com.igorivkin.website.service

interface MarkdownParserService {
    fun parse(input: String): String
}