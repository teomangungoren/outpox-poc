package com.outbox.user_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class UserApiApplication

fun main(args: Array<String>) {
	runApplication<UserApiApplication>(*args)
}
