package com.revy.api_server.app.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Revy on 2023.12.28
 */

@Getter
@NoArgsConstructor
@ToString
public class CalcTotalAmountParamDto {

    // 상품 코드
    private String productCode;

    // 담보 코드 리스트
    private Set<String> collateralCodes;

    // 계약기간
    private Integer contractPeriod;

    // 시작일
    private LocalDate startDate;

    @Builder
    public CalcTotalAmountParamDto(String productCode, Set<String> collateralCodes, Integer contractPeriod, LocalDate startDate) {
        this.productCode = productCode;
        this.collateralCodes = collateralCodes;
        this.contractPeriod = contractPeriod;
        this.startDate = startDate;
    }
}
