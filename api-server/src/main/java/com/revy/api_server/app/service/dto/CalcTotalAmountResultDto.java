package com.revy.api_server.app.service.dto;

import com.revy.api_server.domain.entity.InsuranceCollateral;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Revy on 2023.12.28
 */

@Getter
@NoArgsConstructor
public class CalcTotalAmountResultDto {

    // 상품 정보
    private String productCode; // 상품코드
    private String productName; // 상품명

    private Integer contractPeriod; // 계약기간
    private BigDecimal totalAmount; // 총보험료

    private LocalDate contractStartDate; // 계약 시작일시
    private LocalDate contractEndDate; // 계약 종료 일시

    // 계약 담보정보
    private List<CalcTotalAmountResultDto.ContractCollateralData> contractCollateralList = new ArrayList<>();

    @Builder
    public CalcTotalAmountResultDto(String productCode, String productName, Integer contractPeriod, BigDecimal totalAmount, LocalDate contractStartDate, LocalDate contractEndDate, List<ContractCollateralData> contractCollateralList) {
        this.productCode = productCode;
        this.productName = productName;
        this.contractPeriod = contractPeriod;
        this.totalAmount = totalAmount;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contractCollateralList = contractCollateralList;
    }

    @Getter
    @NoArgsConstructor
    public static class ContractCollateralData {
        private String collateralCode; // 담보 코드
        private String collateralName; // 담보 코드
        private BigDecimal subscriptionAmount;
        private BigDecimal standardAmount;

        @Builder
        public ContractCollateralData(String collateralCode, String collateralName, BigDecimal subscriptionAmount, BigDecimal standardAmount) {
            this.collateralCode = collateralCode;
            this.collateralName = collateralName;
            this.subscriptionAmount = subscriptionAmount;
            this.standardAmount = standardAmount;
        }

        public static CalcTotalAmountResultDto.ContractCollateralData from(InsuranceCollateral insuranceCollateral) {
            return CalcTotalAmountResultDto.ContractCollateralData
                    .builder()
                    .collateralName(insuranceCollateral.getName())
                    .collateralCode(insuranceCollateral.getCode())
                    .subscriptionAmount(insuranceCollateral.getSubscriptionAmount())
                    .standardAmount(insuranceCollateral.getStandardAmount())
                    .build();
        }
    }

}
