package com.synac.quiztimespring.dtos.responses

data class QuizTopicResponse(
    val id: String,
    val name: String,
    val imageUrl: String,
    val code: Int
)