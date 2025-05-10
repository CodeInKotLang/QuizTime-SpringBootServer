package com.synac.quiztimespring.controller

import com.synac.quiztimespring.dtos.IssueReportRequest
import com.synac.quiztimespring.service.IssueReportService
import com.synac.quiztimespring.util.ResponseUtils
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/report/issues")
class IssueReportController(
    private val service: IssueReportService
) {

    @PostMapping
    fun upsert(
        @Valid @RequestBody body: IssueReportRequest
    ): ResponseEntity<Any> {
        return try {
            service.upsert(body)
            ResponseUtils.created(mapOf("message" to "Saved successfully"))
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to submit issue report")
        }
    }

    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        return try {
            val issueReport = service.getAll()
            if (issueReport.isNotEmpty()) {
                ResponseUtils.ok(issueReport)
            } else {
                ResponseUtils.notFound("Issue Report not found")
            }
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to retrieve questions")
        }
    }

    @GetMapping(path = ["/{id}"])
    fun getById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        return try {
            val issueReport = service.getById(id)
            if (issueReport != null) {
                ResponseUtils.ok(issueReport)
            } else {
                ResponseUtils.notFound("Issue Report not found")
            }
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to retrieve reports")
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        return try {
            if (service.deleteById(id)) {
                ResponseUtils.ok(mapOf("message" to "Deleted successfully"))
            } else {
                ResponseUtils.notFound("Quiz question not found")
            }
        } catch (e: Exception) {
            ResponseUtils.internalServerError("Failed to delete question")
        }
    }

}