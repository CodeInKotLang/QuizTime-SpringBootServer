package com.synac.quiztimespring.service

import com.synac.quiztimespring.dtos.requests.QuizTopicRequest
import com.synac.quiztimespring.dtos.responses.QuizTopicResponse

interface QuizTopicService {
    fun upsert(request: QuizTopicRequest)
    fun getAll(): List<QuizTopicResponse>
    fun getById(id: String): QuizTopicResponse
    fun deleteById(id: String)
}