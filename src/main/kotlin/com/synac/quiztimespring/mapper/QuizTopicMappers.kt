package com.synac.quiztimespring.mapper

import com.synac.quiztimespring.controller.QuizTopicRequest
import com.synac.quiztimespring.controller.QuizTopicResponse
import com.synac.quiztimespring.database.model.QuizTopic
import org.bson.types.ObjectId

fun QuizTopic.toResponse() = QuizTopicResponse(
    id = id.toHexString(),
    name = name,
    imageUrl = imageUrl,
    code = code
)

fun QuizTopicRequest.toEntity() = QuizTopic(
    id = id?.let { ObjectId(it) } ?: ObjectId.get(),
    name = name,
    imageUrl = imageUrl,
    code = code
)