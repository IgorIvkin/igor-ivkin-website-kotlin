package com.igorivkin.website.persistence.repository

import com.igorivkin.website.persistence.entity.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicRepository: JpaRepository<Topic, Long> {
    fun findByTitleStartingWithIgnoreCase(title: String): List<Topic>
}