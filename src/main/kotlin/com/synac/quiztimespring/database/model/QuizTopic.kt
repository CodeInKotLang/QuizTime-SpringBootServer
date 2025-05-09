package com.synac.quiztimespring.database.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("quiz_topics")
data class QuizTopic(
    @Id val id: ObjectId = ObjectId.get(),
    val name: String,
    val imageUrl: String,
    val code: Int
)