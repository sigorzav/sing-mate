package com.sigorzav.singmate.util

import com.sigorzav.singmate.model.response.ApiResponse

object ApiResponseUtil {

    inline fun <reified T> ApiResponse<T>.toList(): List<T> {
        return when (val data = this.data) {
            is List<*> -> data.mapNotNull { it as? T }
            null -> emptyList()
            else -> listOf(this.data)
        }
    }
}