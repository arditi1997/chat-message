package com.chat.message.exception;

import lombok.Getter;

public class CustomException extends RuntimeException{
    private final ErrorCode code;
    private String details;

    public CustomException(ErrorCode code, String details){
        super(code.getMessage());
        this.details = details;
        this.code = code;
    }

    public CustomException(ErrorCode code){
        super(code.getMessage());
        this.details = code.getMessage();
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
