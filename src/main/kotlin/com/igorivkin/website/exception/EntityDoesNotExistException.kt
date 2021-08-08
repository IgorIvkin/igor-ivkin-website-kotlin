package com.igorivkin.website.exception

class EntityDoesNotExistException(message: String): Exception(message) {
    companion object {
        fun ofUserId(id: Long) = EntityDoesNotExistException("User with id $id does not exist")
        fun ofTopicId(id: Long) = EntityDoesNotExistException("Topic with id $id does not exist")
        fun ofArticleId(id: Long) = EntityDoesNotExistException("Article with id $id does not exist")
        fun ofCourseId(id: Long) = EntityDoesNotExistException("Course with id $id does not exist")
    }
}