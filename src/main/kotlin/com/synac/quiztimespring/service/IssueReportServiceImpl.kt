package com.synac.quiztimespring.service

import com.synac.quiztimespring.database.repository.IssueReportRepository
import com.synac.quiztimespring.dtos.mappers.toEntity
import com.synac.quiztimespring.dtos.mappers.toResponse
import com.synac.quiztimespring.dtos.requests.IssueReportRequest
import com.synac.quiztimespring.dtos.responses.IssueReportResponse
import com.synac.quiztimespring.util.ResourceNotFoundException
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class IssueReportServiceImpl(
    private val repository: IssueReportRepository
) : IssueReportService {

    override fun upsert(request: IssueReportRequest) {
        repository.save(request.toEntity())
    }

    override fun getAll(): List<IssueReportResponse> {
        return repository.findAll().map { it.toResponse() }
    }

    override fun getById(id: String): IssueReportResponse {
        val issueReport = repository.findById(ObjectId(id)).orElseThrow {
            ResourceNotFoundException("Issue Report not found")
        }
        return issueReport.toResponse()
    }

    override fun deleteById(id: String) {
        if (!repository.existsById(ObjectId(id))) {
            throw ResourceNotFoundException("Issue Report not found")
        }
        repository.deleteById(ObjectId(id))
    }

}
