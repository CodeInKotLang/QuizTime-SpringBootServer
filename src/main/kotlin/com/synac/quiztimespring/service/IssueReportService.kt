package com.synac.quiztimespring.service

import com.synac.quiztimespring.dtos.requests.IssueReportRequest
import com.synac.quiztimespring.dtos.responses.IssueReportResponse

interface IssueReportService {
    fun upsert(request: IssueReportRequest)
    fun getAll(): List<IssueReportResponse>
    fun getById(id: String): IssueReportResponse
    fun deleteById(id: String)
}