package com.outbox.user_api.controller

import com.outbox.user_api.domain.entity.User
import com.outbox.user_api.domain.request.CreatedUserRequest
import com.outbox.user_api.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun saveUser(@RequestBody request: CreatedUserRequest): ResponseEntity<User> {
        return ResponseEntity(userService.save(request), HttpStatus.CREATED)
    }
}