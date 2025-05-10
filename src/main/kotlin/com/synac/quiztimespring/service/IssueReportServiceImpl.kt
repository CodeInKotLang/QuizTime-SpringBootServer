package com.synac.quiztimespring.service

import com.synac.quiztimespring.database.repository.IssueReportRepository
import com.synac.quiztimespring.dtos.IssueReportRequest
import com.synac.quiztimespring.dtos.IssueReportResponse
import com.synac.quiztimespring.dtos.mapper.toEntity
import com.synac.quiztimespring.dtos.mapper.toResponse
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

    override fun getById(id: String): IssueReportResponse? {
        val issueReport = repository.findById(ObjectId(id)).orElse(null)
        return issueReport?.toResponse()
    }

    override fun deleteById(id: String): Boolean {
        return if (repository.existsById(ObjectId(id))) {
            repository.deleteById(ObjectId(id))
            true
        } else false
    }

}
