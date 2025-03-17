package com.sigorzav.singmate.util

enum class HttpStatus(val code: Int, val message: String) {

    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    companion object {
        fun fromCode(code: Int): HttpStatus {
            return entries.find { it.code == code } ?: INTERNAL_SERVER_ERROR
        }
    }
}