package com.revy.api_server.app.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Revy on 2023.12.30
 */
@Getter
@NoArgsConstructor
public class ContractWithdrawalReq {
    private String note; // 청약 철회 사유 등

    public ContractWithdrawalReq(String note) {
        this.note = note;
    }
}
