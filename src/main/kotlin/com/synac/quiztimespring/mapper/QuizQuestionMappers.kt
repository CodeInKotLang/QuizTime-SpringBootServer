package com.synac.quiztimespring.mapper

import com.synac.quiztimespring.controller.QuizQuestionRequest
import com.synac.quiztimespring.controller.QuizQuestionResponse
import com.synac.quiztimespring.database.model.QuizQuestion
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

