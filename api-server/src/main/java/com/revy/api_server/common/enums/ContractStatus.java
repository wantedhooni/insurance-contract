package com.revy.api_server.common.enums;

import lombok.Getter;

/**
 * Created by Revy on 2023.12.28
 * 계약 상태
 * 계약 상태 (정상계약 / 청약철회 / 기간만료)
 */
@Getter
public enum ContractStatus {

    NORMAL("정상 계약"),
    WITHDRAWAL("청약 철회"),
    EXPIRATION("기간 만료");

    private final String description;

    ContractStatus(String description) {
        this.description = description;
    }
}
