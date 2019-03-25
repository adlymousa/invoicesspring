package com.harri.invoicesspring.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

    private List<String> errors;

    public ApiError(){

    }

    public ApiError(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

//    private List<String> errors;
//
//    private HttpStatus status;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
//    private LocalDateTime timestamp;
//    private String message;
//    private String debugMessage;
//
//    private ApiError() {
//        timestamp = LocalDateTime.now();
//    }
//
//    ApiError(HttpStatus status) {
//        this();
//        this.status = status;
//    }
//
//    ApiError(HttpStatus status, Throwable ex) {
//        this();
//        this.status = status;
//        this.message = "Unexpected error";
//        this.debugMessage = ex.getLocalizedMessage();
//    }
//
//    ApiError(HttpStatus status, String message, Throwable ex) {
//        this();
//        this.status = status;
//        this.message = message;
//        this.debugMessage = ex.getLocalizedMessage();
//    }



}