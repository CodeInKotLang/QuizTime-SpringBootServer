package com.synac.quiztimespring.database.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("issue_reports")
data class IssueReport(
    @Id val id: ObjectId = ObjectId.get(),
    val questionId: String,
    val issueType: String,
    val additionalComment: String?,
    val userEmail: String?,
    val timestamp: String
)