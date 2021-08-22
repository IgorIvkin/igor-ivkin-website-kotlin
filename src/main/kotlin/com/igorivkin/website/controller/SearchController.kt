package com.igorivkin.website.controller

import com.igorivkin.website.service.ArticleService
import com.igorivkin.website.view.HtmlBasicView
import com.igorivkin.website.view.HtmlView
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid

@Controller
class SearchController(
    private val articleService: ArticleService
) {
    @GetMapping("/search")
    fun renderSearchResultsPage(model: Model, @Valid @RequestParam(name = "search") search: String): String {
        model.addAttribute("searchQuery", search)
        model.addAttribute("foundArticles", articleService.findBySearchQuery(search))
        val view = HtmlBasicView(model)
        return renderSearchPage(
            view
                .setTitle("Список статей по поисковому запросу")
                .setMainTemplate("main-template")
                .setContent("search-page/main-page :: content")
        );
    }

    private fun renderSearchPage(view: HtmlView): String {
        return view
            .addJs("/js/components/pagination.js")
            .render()
    }
}