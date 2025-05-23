package com.synac.quiztimespring.dtos.mappers

import com.synac.quiztimespring.database.model.QuizQuestion
import com.synac.quiztimespring.dtos.requests.QuizQuestionRequest
import com.synac.quiztimespring.dtos.responses.QuizQuestionResponse
import org.bson.types.ObjectId

fun QuizQuestion.toResponse() = QuizQuestionResponse(
    id = id.toHexString(),
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers,
    explanation = explanation,
    topicCode = topicCode
)

fun QuizQuestionRequest.toEntity() = QuizQuestion(
    id = id?.let { ObjectId(it) } ?: ObjectId.get(),
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers,
    explanation = explanation,
    topicCode = topicCode
)

