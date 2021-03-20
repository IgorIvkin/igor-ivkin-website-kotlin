package com.igorivkin.website.service

import com.igorivkin.website.dto.ArticleDto
import com.igorivkin.website.model.Article

interface ArticleService : BaseService<Article, Long, ArticleDto>  {
}