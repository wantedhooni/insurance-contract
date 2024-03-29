package com.revy.api_server.app.controller.handler;

import com.revy.api_server.app.controller.handler.response.ErrorResponse;
import com.revy.api_server.common.enums.ErrorCode;
import com.revy.api_server.common.exceptions.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * Created by Revy on 2023.12.26
 */

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleBindException(BindingResult bindingResult) {
        log.warn("[handleBindException] {}", bindingResult);
        return ErrorResponse.of(
                ErrorCode.BAD_REQUEST.getCode(),
                Objects.requireNonNullElse(bindingResult.getFieldError().getDefaultMessage(), ErrorCode.BAD_REQUEST.getDescription())
        );
    }

    @ExceptionHandler({CommonException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleCommonException(CommonException e) {
        log.error("[handleCommonException] ", e);
        return ErrorResponse.of(e.getErrorCode().getCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleException(Exception e) {
        log.error("[handleException] ", e);
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }

}
