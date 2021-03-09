package com.igorivkin.website.service

import com.igorivkin.website.dto.TopicDto
import com.igorivkin.website.model.Topic

interface TopicService : BaseService<Topic, Long, TopicDto> {
}