package com.synac.quiztimespring.service

import com.synac.quiztimespring.database.repository.QuizTopicRepository
import com.synac.quiztimespring.dtos.requests.QuizTopicRequest
import com.synac.quiztimespring.dtos.responses.QuizTopicResponse
import com.synac.quiztimespring.dtos.mappers.toEntity
import com.synac.quiztimespring.dtos.mappers.toResponse
import com.synac.quiztimespring.util.ResourceNotFoundException
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class QuizTopicServiceImpl(
    private val repository: QuizTopicRepository
) : QuizTopicService {

    override fun upsert(request: QuizTopicRequest) {
        repository.save(request.toEntity())
    }

    override fun getAll(): List<QuizTopicResponse> {
        val topics = repository.findAllByOrderByCodeAsc()
        return topics.map { it.toResponse() }
    }

    override fun getById(id: String): QuizTopicResponse {
        val topic = repository.findById(ObjectId(id)).orElseThrow {
            ResourceNotFoundException("Quiz topic not found")
        }
        return topic.toResponse()
    }

    override fun deleteById(id: String) {
        if (!repository.existsById(ObjectId(id))) {
            throw ResourceNotFoundException("Quiz topic not found")
        }
        repository.deleteById(ObjectId(id))
    }
}
