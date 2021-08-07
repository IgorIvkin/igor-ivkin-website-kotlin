package com.igorivkin.website.exception

import com.igorivkin.website.persistence.entity.UserRole

class EntityDoesNotExistException(message: String): Exception(message) {
    companion object {
        fun ofUserId(id: Long) = EntityDoesNotExistException("User with id $id does not exist")
        fun ofTopicId(id: Long) = EntityDoesNotExistException("Topic with id $id does not exist")
    }
}