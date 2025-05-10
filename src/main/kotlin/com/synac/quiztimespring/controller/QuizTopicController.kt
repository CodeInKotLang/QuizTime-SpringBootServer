package com.synac.quiztimespring.controller

import com.synac.quiztimespring.dtos.QuizTopicRequest
import com.synac.quiztimespring.service.QuizTopicService
import com.synac.quiztimespring.util.ResponseUtils
import jakarta.validation.Valid
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
        return try {
            service.upsert(body)
            ResponseUtils.created("Quiz topic saved successfully")
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to save quiz topic")
        }
    }

    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        return try {
            val topics = service.getAll()
            if (topics.isNotEmpty()) {
                ResponseUtils.ok(topics)
            } else {
                ResponseUtils.notFound("Quiz topics not found")
            }
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to retrieve topics")
        }
    }

    @GetMapping(path = ["/{id}"])
    fun getById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        return try {
            val topic = service.getById(id)
            if (topic != null) {
                ResponseUtils.ok(topic)
            } else {
                ResponseUtils.notFound("Quiz topic not found")
            }
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to retrieve topic")
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        return try {
            if (service.deleteById(id)) {
                ResponseUtils.ok(mapOf("message" to "Deleted successfully"))
            } else {
                ResponseUtils.notFound("Quiz topic not found")
            }
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to delete question")
        }
    }

}