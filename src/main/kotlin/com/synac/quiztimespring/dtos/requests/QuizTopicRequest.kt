package com.synac.quiztimespring.dtos.requests

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

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