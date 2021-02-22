package com.igorivkin.website.model

/**
 * Represents allowed roles for users.
 * - Administrator has full access to everything
 * - Editor can edit any articles
 * - Plain user can only post the comments
 */
enum class UserRole {
    ROLE_PLAIN,
    ROLE_ADMINISTRATOR,
    ROLE_EDITOR
}