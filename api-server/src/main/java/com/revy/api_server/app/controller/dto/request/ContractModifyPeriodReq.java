package com.revy.api_server.app.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by Revy on 2023.12.30
 */

@Getter
@NoArgsConstructor
public class ContractModifyPeriodReq {
    // 계약기간
    @JsonProperty("contractPeriod")
    @NotNull(message = "계약기간은 필수값입니다.")
    private Integer contractPeriod;
}
