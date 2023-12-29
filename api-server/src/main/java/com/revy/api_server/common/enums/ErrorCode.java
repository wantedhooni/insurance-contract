package com.revy.api_server.common.enums;

import lombok.Getter;

/**
 * Created by Revy on 2023.11.14
 * 에러 코드 
 */
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "서버 내부에 오류가 발생하였습니다."),
    BAD_REQUEST(400, "잘못된 요청 정보입니다.")
    ;

    private final int code;
    private final String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
