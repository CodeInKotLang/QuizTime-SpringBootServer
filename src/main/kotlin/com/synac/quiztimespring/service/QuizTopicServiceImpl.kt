package com.synac.quiztimespring.service

import com.synac.quiztimespring.database.repository.QuizTopicRepository
import com.synac.quiztimespring.dtos.QuizTopicRequest
import com.synac.quiztimespring.dtos.QuizTopicResponse
import com.synac.quiztimespring.dtos.mapper.toEntity
import com.synac.quiztimespring.dtos.mapper.toResponse
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
        return repository.findAllByOrderByCodeAsc()
            .map { it.toResponse() }
    }

    override fun getById(id: String): QuizTopicResponse? {
        val quizTopic = repository.findById(ObjectId(id)).orElse(null)
        return quizTopic?.toResponse()
    }

    override fun deleteById(id: String): Boolean {
        return if (repository.existsById(ObjectId(id))) {
            repository.deleteById(ObjectId(id))
            true
        } else false
    }
}
