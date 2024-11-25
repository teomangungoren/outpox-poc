package com.outbox.user_api.repository

import com.outbox.user_api.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}