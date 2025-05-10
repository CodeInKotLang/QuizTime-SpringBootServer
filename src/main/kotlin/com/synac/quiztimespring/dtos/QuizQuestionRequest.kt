package com.synac.quiztimespring.dtos

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

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