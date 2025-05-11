package com.synac.quiztimespring.controller

import com.synac.quiztimespring.dtos.requests.QuizQuestionRequest
import com.synac.quiztimespring.service.QuizQuestionService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/quiz/questions")
class QuizQuestionController(
    private val service: QuizQuestionService
) {

    @PostMapping
    fun upsert(
        @Valid @RequestBody body: QuizQuestionRequest
    ): ResponseEntity<Any> {
        service.upsert(body)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(mapOf("message" to "Quiz question saved successfully"))
    }

    @PostMapping("/batch")
    fun insertMultiple(
        @Valid @RequestBody body: List<@Valid QuizQuestionRequest>
    ): ResponseEntity<Any> {
        service.insertMultiple(body)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(mapOf("message" to "Batch insert successfully"))
    }

    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        val questions = service.getAll()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(questions)
    }

    @GetMapping("/random")
    fun getRandom(
        @RequestParam(required = false) topicCode: Int?,
        @RequestParam(required = false, defaultValue = "10") limit: Int
    ): ResponseEntity<Any> {
        val questions = service.getRandom(topicCode, limit)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(questions)
    }

    @GetMapping(path = ["/{id}"])
    fun getById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        val question = service.getById(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(question)
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