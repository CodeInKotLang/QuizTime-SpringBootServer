package com.synac.quiztimespring.controller

import com.synac.quiztimespring.dtos.QuizQuestionRequest
import com.synac.quiztimespring.dtos.QuizQuestionResponse
import com.synac.quiztimespring.mapper.toResponse
import com.synac.quiztimespring.service.QuizQuestionService
import com.synac.quiztimespring.util.ResponseUtils
import jakarta.validation.Valid
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
        return try {
            service.upsert(body)
            ResponseUtils.created(mapOf("message" to "Saved successfully"))
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to save question")
        }
    }

    @PostMapping("/batch")
    fun insertMultiple(
        @Valid @RequestBody body: List<@Valid QuizQuestionRequest>
    ): ResponseEntity<Any> {
        return try {
            service.insertMultiple(body)
            ResponseUtils.created(mapOf("message" to "Batch insert successfully"))
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to insert question")
        }
    }

    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        return try {
            val questions = service.getAll()
            if (questions.isNotEmpty()) {
                ResponseUtils.ok(questions)
            } else {
                ResponseUtils.notFound("Quiz questions not found")
            }
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to retrieve questions")
        }
    }

    @GetMapping("/random")
    fun getRandomQuestions(
        @RequestParam(required = false) topicCode: Int?,
        @RequestParam(required = false, defaultValue = "10") limit: Int
    ): ResponseEntity<Any> {
        return try {
            val questions = service.getRandom(topicCode, limit)
            if (questions.isNotEmpty()) {
                ResponseUtils.ok(questions)
            } else {
                ResponseUtils.notFound("Quiz questions not found")
            }
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to retrieve questions")
        }
    }

    @GetMapping(path = ["/{id}"])
    fun getById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        return try {
            val question = service.getById(id)
            if (question != null) {
                ResponseUtils.ok(question)
            } else {
                ResponseUtils.notFound("Quiz question not found")
            }
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to retrieve question")
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        return try {
            service.deleteById(id)
            ResponseEntity.ok(mapOf("message" to "Deleted successfully"))
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to delete question")
        }
    }

}