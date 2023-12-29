package com.revy.api_server.common.exceptions;

import com.revy.api_server.common.enums.ErrorCode;
import lombok.NoArgsConstructor;

/**
 * Created by Revy on 2023.12.29
 * NotFoundException
 */

@NoArgsConstructor
public class NotFoundException extends CommonException{
    public NotFoundException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
