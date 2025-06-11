package io.gffd94.backend.dto

data class GeneralResponses<T>(
    val data: T?,
    val message: String,

)