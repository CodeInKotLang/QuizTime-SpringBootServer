package com.synac.quiztimespring.dtos.mapper

import com.synac.quiztimespring.database.model.QuizTopic
import com.synac.quiztimespring.dtos.QuizTopicRequest
import com.synac.quiztimespring.dtos.QuizTopicResponse
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