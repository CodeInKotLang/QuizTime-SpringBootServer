package com.synac.quiztimespring.controller

import com.synac.quiztimespring.database.repository.QuizTopicRepository
import com.synac.quiztimespring.mapper.toEntity
import com.synac.quiztimespring.mapper.toResponse
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/quiz/topics")
class QuizTopicController(
    private val topicRepository: QuizTopicRepository
) {

    @PostMapping
    fun upsert(
        @Valid @RequestBody body: QuizTopicRequest
    ) {
        topicRepository.save(body.toEntity())
    }

    @GetMapping
    fun getAll(): List<QuizTopicResponse> {
        return topicRepository.findAllByOrderByCodeAsc()
            .map { it.toResponse() }
    }

    @GetMapping(path = ["/{id}"])
    fun getById(
        @PathVariable id: String
    ): QuizTopicResponse {
        val topic = topicRepository.findById(ObjectId(id)).orElseThrow {
            IllegalArgumentException("Quiz Topic not found")
        }
        return topic.toResponse()
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(
        @PathVariable id: String
    ) {
        topicRepository.deleteById(ObjectId(id))
    }

}


data class QuizTopicRequest(
    val id: String?,

    @field:NotBlank(message = "Topic name must not be empty")
    @field:Size(min = 3, message = "Topic name must be at least 3 characters long")
    val name: String,

    @field:NotBlank(message = "Image URL must not be empty")
    val imageUrl: String,

    @field:Min(value = 0, message = "Topic code must be a whole number")
    val code: Int
)

data class QuizTopicResponse(
    val id: String,
    val name: String,
    val imageUrl: String,
    val code: Int
)