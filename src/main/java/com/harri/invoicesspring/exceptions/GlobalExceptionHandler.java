package com.harri.invoicesspring.exceptions;


import com.harri.invoicesspring.models.ApiError;
import com.harri.invoicesspring.models.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//    /**
//     * Provides handling for exceptions throughout this service.
//     *
//     * @param ex The target exception
//     * @param request The current request
//     */
//    @ExceptionHandler({
//
//            SQLException.class
//    })
//    @Nullable
//    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
//        HttpHeaders headers = new HttpHeaders();
//
//        LOGGER.error("Handling " + ex.getClass().getSimpleName() + " due to " + ex.getMessage());
//
//        if(ex instanceof SQLIntegrityConstraintViolationException){
//            HttpStatus status = HttpStatus.CONFLICT;
//            SQLIntegrityConstraintViolationException csicve = (SQLIntegrityConstraintViolationException) ex;
//
//            return handleSQLIntegrityConstraintViolationException(csicve, headers, status, request);
//        }else {
//            if (LOGGER.isWarnEnabled()) {
//                LOGGER.warn("Unknown exception type: " + ex.getClass().getName());
//            }
//
//            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//            return handleExceptionInternal(ex, null, headers, status, request);
//        }
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    /**
     * Customize the response for NotFoundException.
     *
     * @param ex The exception
     * @return a {@code ApiError} instance
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            UserNotFoundException.class,
            FileNotFoundException.class,
            InvoiceNotFoundException.class
    })
    public ApiError handleNotFoundExceptions(Exception ex) {

        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ApiError(errors);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataViolationException.class)
    public ApiError handleDataViolationException(DataViolationException ex) {// that can't be done, data tier exceptions can't go directly to end users.

        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ApiError(errors);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FileStorageException.class)
    public ApiError handleFileStorageException(FileStorageException ex) {

        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ApiError(errors);
    }


//    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, @Nullable ApiError body,
//                                                               HttpHeaders headers, HttpStatus status,
//                                                               WebRequest request) {
//        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
//            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
//        }
//
//        return new ResponseEntity<>(body, headers, status);
//    }

}