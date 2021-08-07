package com.igorivkin.website.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean

@SpringBootTest(classes = [MarkdownParserServiceImpl::class])
class MarkdownParserServiceTests {

    @SpyBean
    private lateinit var markdownParserService: MarkdownParserService

    @Test
    fun parseSimpleBoldTextTest() {
        val input = "Hello *world*!"
        val outputActual = markdownParserService.parse(input)
        assertEquals(outputActual, "<p>Hello <em>world</em>!</p>\n")
    }
}