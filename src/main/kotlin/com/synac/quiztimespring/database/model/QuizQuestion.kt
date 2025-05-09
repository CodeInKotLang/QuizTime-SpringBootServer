package com.synac.quiztimespring.database.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("quiz_questions")
data class QuizQuestion(
    @Id val id: ObjectId = ObjectId.get(),
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val explanation: String,
    val topicCode: Int
)
