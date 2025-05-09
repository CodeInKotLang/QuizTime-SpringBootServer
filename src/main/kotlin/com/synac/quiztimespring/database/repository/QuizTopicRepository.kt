package com.synac.quiztimespring.database.repository

import com.synac.quiztimespring.database.model.QuizTopic
import com.synac.quiztimespring.entity.QuizTopicEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface QuizTopicRepository : MongoRepository<QuizTopic, ObjectId> {
    fun findAllByOrderByCodeAsc(): List<QuizTopic>
}