package com.synac.quiztimespring.controller

import com.synac.quiztimespring.database.repository.QuizQuestionRepository
import com.synac.quiztimespring.mapper.toEntity
import com.synac.quiztimespring.mapper.toResponse
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/quiz/questions")
class QuizQuestionController(
    private val questionRepository: QuizQuestionRepository
) {

    @PostMapping
    fun upsert(
        @Valid @RequestBody body: QuizQuestionRequest
    ) {
        questionRepository.save(body.toEntity())
    }

    @PostMapping("/batch")
    fun insertMultiple(
        @Valid @RequestBody body: List<@Valid QuizQuestionRequest>
    ) {
        val entities = body.map { it.toEntity() }
        questionRepository.saveAll(entities)
    }

    @GetMapping
    fun getAll(): List<QuizQuestionResponse> {
        return questionRepository.findAll()
            .map { it.toResponse() }
    }

    @GetMapping("/random")
    fun getRandomQuestions(
        @RequestParam(required = false) topicCode: Int?,
        @RequestParam(required = false, defaultValue = "10") limit: Int
    ): List<QuizQuestionResponse> {
        val questions = if (topicCode != null) {
            questionRepository.findByTopicCode(topicCode)
        } else {
            questionRepository.findAll()
        }

        return questions.shuffled().take(limit).map { it.toResponse() }
    }

    @GetMapping(path = ["/{id}"])
    fun getById(
        @PathVariable id: String
    ): QuizQuestionResponse {
        val question = questionRepository.findById(ObjectId(id)).orElseThrow {
            IllegalArgumentException("Quiz Topic not found")
        }
        return question.toResponse()
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(
        @PathVariable id: String
    ) {
        questionRepository.deleteById(ObjectId(id))
    }

}

data class QuizQuestionRequest(
    val id: String? = null,

    @field:NotBlank(message = "Question must not be empty")
    @field:Size(min = 5, message = "Question must be at least 5 characters long")
    val question: String,

    @field:NotBlank(message = "Correct answer must not be empty")
    val correctAnswer: String,

    @field:NotEmpty(message = "There must be at least one incorrect answer")
    val incorrectAnswers: List<@NotBlank(message = "Incorrect answers must not be empty") String>,

    @field:NotBlank(message = "Explanation must not be empty")
    val explanation: String,

    @field:Min(value = 1, message = "Topic code must be a positive integer")
    val topicCode: Int
)

data class QuizQuestionResponse(
    val id: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val explanation: String,
    val topicCode: Int
)