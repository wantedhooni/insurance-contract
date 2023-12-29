package com.revy.api_server.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.api_server.app.service.dto.ContractCreateResultDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Revy on 2023.12.29
 */

@Getter
@NoArgsConstructor
public class CreateContractRes {

    // 상품 코드
    @JsonProperty
    private String productCode;

    // 담보 코드 리스트
    @JsonProperty
    private List<String> CollateralCodes;

    // 계약기간
    @JsonProperty
    private Integer contractPeriod;

    // 시작일
    @JsonProperty
    private LocalDate startDate;

    // 종료일
    @JsonProperty
    private LocalDate endDate;

    // 총보험료
    @JsonProperty
    private BigDecimal totalAmount;

    @Builder
    public CreateContractRes(String productCode, List<String> collateralCodes, Integer contractPeriod, LocalDate startDate, LocalDate endDate, BigDecimal totalAmount) {
        this.productCode = productCode;
        CollateralCodes = collateralCodes;
        this.contractPeriod = contractPeriod;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
    }

    public static CreateContractRes from(ContractCreateResultDto resultDto){
        return CreateContractRes.builder().build();
    }
}
