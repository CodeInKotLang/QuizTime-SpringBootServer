package com.synac.quiztimespring.service

import com.synac.quiztimespring.database.repository.QuizQuestionRepository
import com.synac.quiztimespring.dtos.mappers.toEntity
import com.synac.quiztimespring.dtos.mappers.toResponse
import com.synac.quiztimespring.dtos.requests.QuizQuestionRequest
import com.synac.quiztimespring.dtos.responses.QuizQuestionResponse
import com.synac.quiztimespring.util.ResourceNotFoundException
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class QuizQuestionServiceImpl(
    private val repository: QuizQuestionRepository
) : QuizQuestionService {

    override fun upsert(question: QuizQuestionRequest) {
        repository.save(question.toEntity())
    }

    override fun insertMultiple(questions: List<QuizQuestionRequest>) {
        val entities = questions.map { it.toEntity() }
        repository.saveAll(entities)
    }

    override fun getAll(): List<QuizQuestionResponse> {
        return repository.findAll().map { it.toResponse() }
    }

    override fun getRandom(topicCode: Int?, limit: Int): List<QuizQuestionResponse> {
        val questions = topicCode?.let {
            repository.findByTopicCode(topicCode)
        } ?: repository.findAll()
        return questions.shuffled().take(limit).map { it.toResponse() }
    }

    override fun getById(id: String): QuizQuestionResponse {
        val question = repository.findById(ObjectId(id)).orElseThrow {
            ResourceNotFoundException("Quiz question not found")
        }
        return question.toResponse()
    }

    override fun deleteById(id: String) {
        if (!repository.existsById(ObjectId(id))) {
            throw ResourceNotFoundException("Quiz question not found")
        }
        repository.deleteById(ObjectId(id))
    }

}
