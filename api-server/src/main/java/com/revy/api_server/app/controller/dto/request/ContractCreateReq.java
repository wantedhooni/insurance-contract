package com.revy.api_server.app.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.api_server.app.service.dto.ContractCreateParamDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Revy on 2023.12.29
 */

@Getter
@NoArgsConstructor
@ToString
public class ContractCreateReq {

    // 상품 코드
    @JsonProperty("productCode")
    @NotBlank(message = "상품코드는 필수값 입니다.")
    private String productCode;

    // 담보 코드 리스트
    @JsonProperty("collateralCodes")
    @NotEmpty(message = "담보코드는 필수값입니다.")
    private Set<String> collateralCodes;

    // 계약기간
    @JsonProperty("contractPeriod")
    @NotNull(message = "계약기간은 필수값입니다.")
    private Integer contractPeriod;

    // 시작일
    @JsonProperty("startDate")
    @NotNull(message = "계약 시작일은 필수값입니다.")
    private LocalDate startDate;

    public ContractCreateParamDto toContractCreateParamDto() {
        return ContractCreateParamDto
                .builder()
                .productCode(this.productCode)
                .collateralCodes(this.collateralCodes)
                .contractPeriod(this.contractPeriod)
                .startDate(this.startDate)
                .build();
    }
}
