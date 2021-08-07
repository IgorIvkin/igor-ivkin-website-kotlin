package com.igorivkin.website.persistence.entity

/**
 * Represents allowed roles for users.
 * - Administrator has full access to everything
 * - Editor can edit any articles
 * - Plain user can only post the comments
 */
enum class UserRole(val value: Int) {
    PLAIN(1),
    ROLE_ADMINISTRATOR(2),
    ROLE_EDITOR(3);

    companion object {
        fun of(value: Int) = UserRole.values().first { it.value == value }
    }
}