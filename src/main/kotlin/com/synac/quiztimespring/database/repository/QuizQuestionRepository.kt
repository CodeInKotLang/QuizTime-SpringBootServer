package com.synac.quiztimespring.database.repository

import com.synac.quiztimespring.database.model.QuizQuestion
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizQuestionRepository: MongoRepository<QuizQuestion, ObjectId> {
    fun findByTopicCode(topicCode: Int): List<QuizQuestion>
}