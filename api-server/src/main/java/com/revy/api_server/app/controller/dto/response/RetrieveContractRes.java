package com.revy.api_server.app.controller.dto.response;

import com.revy.api_server.app.service.dto.ContractRetrieveResultDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Revy on 2023.12.29
 */

@Getter
@NoArgsConstructor
public class RetrieveContractRes {
    public static RetrieveContractRes from(ContractRetrieveResultDto resultDto) {
        return null;
    }
}
