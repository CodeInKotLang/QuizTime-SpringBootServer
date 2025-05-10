package com.synac.quiztimespring.service

import com.synac.quiztimespring.dtos.QuizQuestionRequest
import com.synac.quiztimespring.dtos.QuizQuestionResponse

interface QuizQuestionService {
    fun upsert(question: QuizQuestionRequest)
    fun insertMultiple(questions: List<QuizQuestionRequest>)
    fun getAll(): List<QuizQuestionResponse>
    fun getRandom(topicCode: Int?, limit: Int): List<QuizQuestionResponse>
    fun getById(id: String): QuizQuestionResponse?
    fun deleteById(id: String): Boolean
}
