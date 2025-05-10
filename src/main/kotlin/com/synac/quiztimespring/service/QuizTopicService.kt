package com.synac.quiztimespring.service

import com.synac.quiztimespring.dtos.QuizTopicRequest
import com.synac.quiztimespring.dtos.QuizTopicResponse

interface QuizTopicService {
    fun upsert(request: QuizTopicRequest)
    fun getAll(): List<QuizTopicResponse>
    fun getById(id: String): QuizTopicResponse?
    fun deleteById(id: String): Boolean
}