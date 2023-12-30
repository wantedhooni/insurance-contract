package com.revy.api_server.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.api_server.app.service.dto.ContractResultDto;
import com.revy.api_server.common.enums.ContractStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Revy on 2023.12.29
 */

@Getter
@NoArgsConstructor
public class ContractInfoRes extends CommonRes {

    // 상품 정보
    @JsonProperty("productName")
    private String productName;

    // 계약정보
    @JsonProperty("contractNo")
    private String contractNo; // 계약번호
    @JsonProperty("contractStatus")
    private ContractStatus contractStatus; // 계약 상태
    @JsonProperty("contractPeriod")
    private Integer contractPeriod; // 계약기간
    @JsonProperty("totalAmount")
    private BigDecimal totalAmount; // 총보험료

    @JsonProperty("contractStartDate")
    private LocalDate contractStartDate; // 계약 시작일시
    @JsonProperty("contractEndDate")
    private LocalDate contractEndDate; // 계약 종료 일시
    @JsonProperty("contracted")
    private LocalDateTime contracted; // 계약 일시

    @JsonProperty("contractCollateralResDatas")
    private List<ContractCollateralResData> contractCollateralResDatas = new ArrayList<>();

    @Builder
    public ContractInfoRes(String productName, String contractNo, ContractStatus contractStatus, Integer contractPeriod, BigDecimal totalAmount, LocalDate contractStartDate, LocalDate contractEndDate, LocalDateTime contracted, List<ContractCollateralResData> contractCollateralResDatas) {
        this.productName = productName;
        this.contractNo = contractNo;
        this.contractStatus = contractStatus;
        this.contractPeriod = contractPeriod;
        this.totalAmount = totalAmount;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contracted = contracted;
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

        public static ContractCollateralResData from(ContractResultDto.ContractCollateralData contractCollateralData) {
            return ContractCollateralResData
                    .builder()
                    .collateralName(contractCollateralData.getCollateralName())
                    .subscriptionAmount(contractCollateralData.getSubscriptionAmount())
                    .standardAmount(contractCollateralData.getStandardAmount())
                    .build();
        }
    }

    public static ContractInfoRes from(ContractResultDto contractCreateResultDto) {

        List<ContractCollateralResData> contractCollateralResDataList = contractCreateResultDto.getContractCollateralList().stream().map(data -> ContractCollateralResData.from(data)).toList();
        return ContractInfoRes
                .builder()
                .productName(contractCreateResultDto.getProductName())
                .contractNo(contractCreateResultDto.getContractNo())
                .contractStatus(contractCreateResultDto.getContractStatus())
                .contractPeriod(contractCreateResultDto.getContractPeriod())
                .totalAmount(contractCreateResultDto.getTotalAmount())
                .contractStartDate(contractCreateResultDto.getContractStartDate())
                .contractEndDate(contractCreateResultDto.getContractEndDate())
                .contracted(contractCreateResultDto.getContracted())
                .contractCollateralResDatas(contractCollateralResDataList)
                .build();
    }
}
