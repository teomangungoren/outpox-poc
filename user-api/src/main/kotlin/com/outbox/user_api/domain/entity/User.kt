package com.outbox.user_api.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @GeneratedValue
    @Id
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
)
