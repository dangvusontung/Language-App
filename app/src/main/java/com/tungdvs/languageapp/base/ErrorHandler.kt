package com.tungdvs.languageapp.base

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHandler @Inject constructor() {
    fun handleError(error: Throwable): AppError {
        return when (error) {
            is retrofit2.HttpException -> {
                when (error.code()) {
                    in 400..499 -> AppError.ServerError(error.message(), error.code())
                    in 500..599 -> AppError.NetworkError(error.message(), error.code())
                    else -> AppError.UnknownError(error)
                }
            }
            is java.net.SocketTimeoutException -> AppError.NetworkError("Network timeout", -1)
            is java.io.IOException -> AppError.NetworkError("Network error", -1)
            is AppError -> error
            else -> AppError.UnknownError(error)
        }
    }

    fun getErrorMessage(error: AppError): String {
        return when (error) {
            is AppError.NetworkError -> "Network error: ${error.message}"
            is AppError.ServerError -> "Server error: ${error.message}"
            is AppError.DatabaseError -> "Database error: ${error.message}"
            is AppError.ValidationError -> error.errors.values.joinToString("\n")
            is AppError.UnknownError -> "An unknown error occurred"
        }
    }
}