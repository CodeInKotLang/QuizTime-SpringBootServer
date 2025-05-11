package com.synac.quiztimespring.dtos.responses

data class ErrorResponse(
    val status: Int,
    val message: String,
    val errors: List<String>? = null
)