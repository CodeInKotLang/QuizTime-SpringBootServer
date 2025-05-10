package com.synac.quiztimespring.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object ResponseUtils {

    fun ok(body: Any): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.OK).body(body)

    fun created(body: Any): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.CREATED).body(body)

    fun badRequest(message: String): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to message))

    fun notFound(message: String): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to message))

    fun internalServerError(message: String): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("error" to message))
}
