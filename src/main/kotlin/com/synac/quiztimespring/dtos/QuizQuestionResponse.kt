package com.synac.quiztimespring.dtos

data class QuizQuestionResponse(
    val id: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val explanation: String,
    val topicCode: Int
)