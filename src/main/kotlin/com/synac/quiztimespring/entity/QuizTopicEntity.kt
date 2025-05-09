package com.synac.quiztimespring.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "quiz_topics")
data class QuizTopicEntity(
    @Id
    val id: String? = null,
    val name: String,
    val imageUrl: String,
    val code: Int
)