package com.example.yit.data.models

import com.example.yit.domain.models.Gallery


sealed class Response {
    data class Success(val data: Gallery) : Response()
    data class Error(val error: Throwable) : Response()
}