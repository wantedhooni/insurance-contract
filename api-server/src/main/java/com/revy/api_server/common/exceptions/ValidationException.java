package com.revy.api_server.common.exceptions;

import com.revy.api_server.common.enums.ErrorCode;
import lombok.NoArgsConstructor;

/**
 * Created by Revy on 2023.12.29
 * ValidationException
 */

@NoArgsConstructor
public class ValidationException extends CommonException{
    public ValidationException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
