package com.synac.quiztimespring.dtos

data class IssueReportResponse(
    val id: String,
    val questionId: String,
    val issueType: String,
    val additionalComment: String?,
    val userEmail: String?,
    val timestamp: String
)