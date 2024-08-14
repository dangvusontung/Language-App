package com.tungdvs.languageapp.base

sealed class AppError : Exception() {
    data class NetworkError(override val message: String, val code: Int) : AppError()
    data class ServerError(override val message: String, val code: Int) : AppError()
    data class DatabaseError(override val message: String) : AppError()
    data class ValidationError(val errors: Map<String, String>) : AppError()
    data class UnknownError(override val cause: Throwable?) : AppError()
}