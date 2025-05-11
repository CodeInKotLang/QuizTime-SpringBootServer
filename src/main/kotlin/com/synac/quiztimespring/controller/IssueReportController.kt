package com.synac.quiztimespring.controller

import com.synac.quiztimespring.dtos.requests.IssueReportRequest
import com.synac.quiztimespring.service.IssueReportService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
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
        service.upsert(body)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(mapOf("message" to "Issue report saved successfully"))
    }

    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        val issueReport = service.getAll()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(issueReport)
    }

    @GetMapping(path = ["/{id}"])
    fun getById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        val issueReport = service.getById(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(issueReport)
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        service.deleteById(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(mapOf("message" to "Deleted successfully"))
    }

}