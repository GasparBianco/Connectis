package com.connectis.connectis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    ProblemDetail handleThrowable(Throwable throwable) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        problemDetail.setTitle(HttpStatus.INTERNAL_SERVER_ERROR.name());
        return problemDetail;
    }

    @ExceptionHandler(ProvinceNotFoundException.class)
    ProblemDetail handleProvinceNotFoundException(ProvinceNotFoundException throwable) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Province not found");
        problemDetail.setTitle("COUNTRY_NOT_FOUND");
        return problemDetail;
    }

}
