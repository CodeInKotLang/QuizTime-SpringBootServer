package com.synac.quiztimespring.service

import com.synac.quiztimespring.dtos.requests.QuizQuestionRequest
import com.synac.quiztimespring.dtos.responses.QuizQuestionResponse

interface QuizQuestionService {
    fun upsert(question: QuizQuestionRequest)
    fun insertMultiple(questions: List<QuizQuestionRequest>)
    fun getAll(): List<QuizQuestionResponse>
    fun getRandom(topicCode: Int?, limit: Int): List<QuizQuestionResponse>
    fun getById(id: String): QuizQuestionResponse
    fun deleteById(id: String)
}
