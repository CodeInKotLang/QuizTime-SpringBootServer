package com.synac.quiztimespring.service

import com.synac.quiztimespring.database.repository.QuizQuestionRepository
import com.synac.quiztimespring.dtos.QuizQuestionRequest
import com.synac.quiztimespring.dtos.QuizQuestionResponse
import com.synac.quiztimespring.dtos.mapper.toEntity
import com.synac.quiztimespring.dtos.mapper.toResponse
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
        val questions = if (topicCode != null) {
            repository.findByTopicCode(topicCode)
        } else {
            repository.findAll()
        }
        return questions.shuffled().take(limit).map { it.toResponse() }
    }

    override fun getById(id: String): QuizQuestionResponse? {
        val question = repository.findById(ObjectId(id)).orElse(null)
        return question?.toResponse()
    }

    override fun deleteById(id: String): Boolean {
        return if (repository.existsById(ObjectId(id))) {
            repository.deleteById(ObjectId(id))
            true
        } else false
    }

}
