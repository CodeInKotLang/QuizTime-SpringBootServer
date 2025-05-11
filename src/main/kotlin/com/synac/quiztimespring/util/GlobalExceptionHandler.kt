package com.synac.quiztimespring.util

import com.synac.quiztimespring.dtos.responses.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleInternalServerError(e: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = 500,
            message = "An unexpected Error Occurred"
        )
        return ResponseEntity.status(500).body(errorResponse)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationError(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult.allErrors.map {
            it.defaultMessage ?: "Invalid value"
        }
        val errorResponse = ErrorResponse(
            status = 400,
            message = "Validation failed",
            errors = errors
        )
        return ResponseEntity.status(400).body(errorResponse)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(ex: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = 404,
            message = ex.message ?: "Resource not found"
        )
        return ResponseEntity.status(404).body(errorResponse)
    }

}

class ResourceNotFoundException(message: String) : RuntimeException(message)

//TODO add logger for internal server error