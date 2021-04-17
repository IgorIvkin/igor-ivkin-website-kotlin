package com.igorivkin.website.service

import org.commonmark.Extension
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.node.Node
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.springframework.stereotype.Component

@Component
class MarkdownParserServiceImpl: MarkdownParserService {
    override fun parse(input: String): String {
        val extensions: List<Extension> = listOf(TablesExtension.create())
        val parser = Parser.builder()
            .extensions(extensions)
            .build()
        val htmlDocument: Node = parser.parse(input)
        val renderer = HtmlRenderer.builder()
            .extensions(extensions)
            .build()
        return renderer.render(htmlDocument)
    }
}