package com.harri.invoicesspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataViolationException extends Exception {

    public DataViolationException(String message){ super(message); }

    public DataViolationException(String message, Throwable cause){ super(message, cause); }
}
