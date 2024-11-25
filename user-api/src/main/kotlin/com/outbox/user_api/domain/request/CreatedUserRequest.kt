package com.outbox.user_api.domain.request

import com.outbox.user_api.domain.entity.User

data class CreatedUserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
) {
    companion object {
        fun toUser(request: CreatedUserRequest): User {
            return with(request) {
                User(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                )
            }
        }
    }
}
