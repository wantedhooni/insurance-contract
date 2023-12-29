package com.revy.api_server.app.controller.handler.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.api_server.common.enums.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Revy on 2023.12.29
 */
@Getter
@NoArgsConstructor
public class ErrorResponse {
    @JsonProperty("code")
    private int code;
    @JsonProperty("message")
    private String message;

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(int code, String message) {
        return new ErrorResponse(code, message);
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getDescription());
    }
}
