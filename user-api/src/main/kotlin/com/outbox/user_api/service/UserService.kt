package com.outbox.user_api.service

import com.outbox.user_api.domain.entity.User
import com.outbox.user_api.domain.request.CreatedUserRequest
import com.outbox.user_api.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val outboxService: OutboxService) {

    fun save(request: CreatedUserRequest): User {
        val user = CreatedUserRequest.toUser(request)
        val savedUser = userRepository.save(user)
        outboxService.save(savedUser)
        return savedUser
    }
}