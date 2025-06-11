package io.gffd94.backend.exceptions

import io.gffd94.backend.dto.GeneralResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MemberExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElementException(
        e: MemberNotFoundException
    ): GeneralResponses<Void>{
        return GeneralResponses(
            data = null,
            message = e.message!!
        )
    }


}