package com.synac.quiztimespring.controller

import com.synac.quiztimespring.dtos.requests.QuizTopicRequest
import com.synac.quiztimespring.service.QuizTopicService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/quiz/topics")
class QuizTopicController(
    private val service: QuizTopicService
) {

    @PostMapping
    fun upsert(
        @Valid @RequestBody body: QuizTopicRequest
    ): ResponseEntity<Any> {
        service.upsert(body)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(mapOf("message" to "Quiz topic saved successfully"))
    }

    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        val topics = service.getAll()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(topics)
    }

    @GetMapping(path = ["/{id}"])
    fun getById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        val topic = service.getById(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(topic)
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        service.deleteById(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(mapOf("message" to "Deleted successfully"))
    }

}