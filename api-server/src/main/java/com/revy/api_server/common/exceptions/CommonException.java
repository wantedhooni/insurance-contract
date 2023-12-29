package com.revy.api_server.common.exceptions;

import com.revy.api_server.common.enums.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Revy on 2023.12.29
 * 공통 Exception
 */

@Getter
@Setter
@NoArgsConstructor
public class CommonException extends RuntimeException {
    protected ErrorCode errorCode;

    public CommonException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
