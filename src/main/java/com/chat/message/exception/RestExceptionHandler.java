package com.chat.message.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleCustomException(CustomException e){
        logger.error("Custom Runtime Exception thrown: {}", e);
        return new ErrorResponseDto(e.getCode().getCode(), e.getDetails());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleRequestBodyNotValidException(MethodArgumentNotValidException e){
        logger.warn(e.getMessage());
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(objectError ->
                    errors.add(objectError.getDefaultMessage()));
        errorResponseDto.setErrorCode(ErrorCode.BAD_REQUEST.getCode());
        errorResponseDto.setMessage(ErrorCode.BAD_REQUEST.getMessage());
        errorResponseDto.setErrors(errors);

        return errorResponseDto;
    }
}
