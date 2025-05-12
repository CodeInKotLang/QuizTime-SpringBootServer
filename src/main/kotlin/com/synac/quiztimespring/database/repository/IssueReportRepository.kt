package com.synac.quiztimespring.database.repository

import com.synac.quiztimespring.database.model.IssueReport
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface IssueReportRepository: MongoRepository<IssueReport, ObjectId>