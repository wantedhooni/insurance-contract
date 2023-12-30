package com.revy.api_server.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.api_server.app.service.dto.CalcTotalAmountResultDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Revy on 2023.12.29
 */

@Getter
@NoArgsConstructor
public class CalcTotalAmountRes {

    @JsonProperty("productName")
    private String productName; //  상품명
    @JsonProperty("productCode")
    private String productCode; // 상품코드

    @JsonProperty("contractPeriod")
    private Integer contractPeriod; // 계약기간
    @JsonProperty("totalAmount")
    private BigDecimal totalAmount; // 총보험료

    @JsonProperty("contractStartDate")
    private LocalDate contractStartDate; // 계약 시작일시
    @JsonProperty("contractEndDate")
    private LocalDate contractEndDate; // 계약 종료 일시

    @JsonProperty("contractCollateralResDatas")
    private List<ContractCollateralResData> contractCollateralResDatas = new ArrayList<>();

    @Builder
    public CalcTotalAmountRes(String productName, String productCode, Integer contractPeriod, BigDecimal totalAmount, LocalDate contractStartDate, LocalDate contractEndDate, List<ContractCollateralResData> contractCollateralResDatas) {
        this.productName = productName;
        this.productCode = productCode;
        this.contractPeriod = contractPeriod;
        this.totalAmount = totalAmount;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contractCollateralResDatas = contractCollateralResDatas;
    }

    @Getter
    @NoArgsConstructor
    public static class ContractCollateralResData {
        @JsonProperty("collateralName")
        private String collateralName; // 담보명
        @JsonProperty("subscriptionAmount")
        private BigDecimal subscriptionAmount; // 담보 금액
        @JsonProperty("standardAmount")
        private BigDecimal standardAmount; // 담보 금액

        @Builder
        public ContractCollateralResData(String collateralName, BigDecimal subscriptionAmount, BigDecimal standardAmount) {
            this.collateralName = collateralName;
            this.subscriptionAmount = subscriptionAmount;
            this.standardAmount = standardAmount;
        }

        public static ContractCollateralResData from(CalcTotalAmountResultDto.ContractCollateralData contractCollateralData) {
            return ContractCollateralResData
                    .builder()
                    .collateralName(contractCollateralData.getCollateralName())
                    .subscriptionAmount(contractCollateralData.getSubscriptionAmount())
                    .standardAmount(contractCollateralData.getStandardAmount())
                    .build();
        }
    }

    public static CalcTotalAmountRes from(CalcTotalAmountResultDto calcTotalAmountResultDto) {
        List<ContractCollateralResData> contractCollateralResDataList = calcTotalAmountResultDto.getContractCollateralList().stream().map(data -> ContractCollateralResData.from(data)).toList();
        return CalcTotalAmountRes
                .builder()
                .productName(calcTotalAmountResultDto.getProductName())
                .productCode(calcTotalAmountResultDto.getProductCode())
                .contractPeriod(calcTotalAmountResultDto.getContractPeriod())
                .totalAmount(calcTotalAmountResultDto.getTotalAmount())
                .contractStartDate(calcTotalAmountResultDto.getContractStartDate())
                .contractEndDate(calcTotalAmountResultDto.getContractEndDate())
                .contractCollateralResDatas(contractCollateralResDataList)
                .build();
    }
}
