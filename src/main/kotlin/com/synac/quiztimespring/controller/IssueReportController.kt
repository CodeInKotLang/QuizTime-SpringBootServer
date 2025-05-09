package com.synac.quiztimespring.controller

import com.synac.quiztimespring.database.repository.IssueReportRepository
import com.synac.quiztimespring.mapper.toEntity
import com.synac.quiztimespring.mapper.toResponse
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/report/issues")
class IssueReportController(
    private val issueReportRepository: IssueReportRepository
) {

    @PostMapping
    fun upsert(
        @Valid @RequestBody body: IssueReportRequest
    ) {
        issueReportRepository.save(body.toEntity())
    }

    @GetMapping
    fun getAll(): List<IssueReportResponse> {
        return issueReportRepository.findAll()
            .map { it.toResponse() }
    }

    @GetMapping(path = ["/{id}"])
    fun getById(
        @PathVariable id: String
    ): IssueReportResponse {
        val topic = issueReportRepository.findById(ObjectId(id)).orElseThrow {
            IllegalArgumentException("Quiz Topic not found")
        }
        return topic.toResponse()
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(
        @PathVariable id: String
    ) {
        issueReportRepository.deleteById(ObjectId(id))
    }

}

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

data class IssueReportResponse(
    val id: String,
    val questionId: String,
    val issueType: String,
    val additionalComment: String?,
    val userEmail: String?,
    val timestamp: String
)
