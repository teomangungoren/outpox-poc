package com.outbox.user_api.domain.entity

import com.outbox.user_api.domain.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Outbox(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: Long,
    @Enumerated(EnumType.STRING)
    var status: Status? = Status.PENDING,
    @Column(length = 200)
    val payload: String
)