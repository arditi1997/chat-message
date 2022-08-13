package com.chat.message.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
public class ErrorResponseDto {

    private long errorCode;

    private String message;

    private List<String> errors;

    public ErrorResponseDto() {

    }

    public ErrorResponseDto(long code, String details) {
        this.errorCode= code;
        this.message = details;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
