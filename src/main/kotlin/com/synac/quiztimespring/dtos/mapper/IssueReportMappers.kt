package com.synac.quiztimespring.dtos.mapper

import com.synac.quiztimespring.database.model.IssueReport
import com.synac.quiztimespring.dtos.IssueReportRequest
import com.synac.quiztimespring.dtos.IssueReportResponse
import org.bson.types.ObjectId

fun IssueReport.toResponse() = IssueReportResponse(
    id = id.toHexString(),
    questionId = questionId,
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail = userEmail,
    timestamp = timestamp
)

fun IssueReportRequest.toEntity() = IssueReport(
    id = id?.let { ObjectId(it) } ?: ObjectId.get(),
    questionId = questionId,
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail = userEmail,
    timestamp = timestamp
)