package com.igorivkin.website.view

interface HtmlView {
    fun render(): String

    fun setMainTemplate(mainTemplateName: String): HtmlView
    fun setTitle(title: String): HtmlView
    fun setHeader(headerTemplateName: String): HtmlView
    fun setFooter(footerTemplateName: String): HtmlView
    fun setJavascriptData(javaScriptData: String): HtmlView
    fun setContent(contentTemplateName: String): HtmlView
    fun addJs(jsFileName: String): HtmlView
}