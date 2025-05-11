package com.synac.quiztimespring.dtos.requests

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class IssueReportRequest(

    val id: String?,

    @field:NotBlank(message = "Question ID must not be empty")
    val questionId: String,

    @field:NotBlank(message = "Issue type must not be empty")
    val issueType: String,

    @field:Size(min = 5, message = "Additional Comment must be at least 5 characters long")
    val additionalComment: String? = null,

    @field:Email(message = "Invalid email format")
    val userEmail: String? = null,

    @field:NotBlank(message = "Timestamp must not be empty")
    val timestamp: String
)