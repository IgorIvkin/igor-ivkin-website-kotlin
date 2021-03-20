package com.igorivkin.website.view

import org.springframework.ui.Model


open class HtmlBasicView(
    private val model: Model
): HtmlView {

    private lateinit var mainTemplate: String
    private val js: MutableList<String> = ArrayList()

    override fun render(): String {
        beforeRender()
        return mainTemplate
    }

    override fun setMainTemplate(mainTemplateName: String): HtmlView {
        mainTemplate = mainTemplateName
        return this
    }

    override fun setTitle(title: String): HtmlView {
        initModelWithObjectParameter("pageTitle", title)
        return this
    }

    override fun setHeader(headerTemplateName: String): HtmlView {
        initModelWithObjectParameter("headerTemplateName", headerTemplateName)
        return this
    }

    override fun setFooter(footerTemplateName: String): HtmlView {
        initModelWithObjectParameter("footerTemplateName", footerTemplateName)
        return this
    }

    override fun setContent(contentTemplateName: String): HtmlView {
        initModelWithObjectParameter("contentTemplateName", contentTemplateName)
        return this
    }

    override fun addJs(jsFileName: String): HtmlView {
        js.add(jsFileName)
        return this
    }

    /**
     * Initializes a current model in this view by the parameter with a given value and name.
     * @param parameterName String presentation of name of the parameter.
     * @param parameterValue Actual value of parameter to add to model.
     * @return Current view object.
     */
    private fun initModelWithObjectParameter(parameterName: String,
                                             parameterValue: Any): HtmlView {
        model.addAttribute(parameterName, parameterValue)
        return this
    }

    private fun beforeRender() {
        initModelWithObjectParameter("javascript", js)
    }

}