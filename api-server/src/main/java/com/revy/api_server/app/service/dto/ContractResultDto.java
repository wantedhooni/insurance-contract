package com.revy.api_server.app.service.dto;

import com.revy.api_server.common.enums.ContractStatus;
import com.revy.api_server.domain.entity.Contract;
import com.revy.api_server.domain.entity.ContractCollateral;
import com.revy.api_server.domain.entity.InsuranceProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Revy on 2023.12.28
 */

@Getter
@NoArgsConstructor
public class ContractResultDto {

    // 상품 정보
    private String productName;
    private String productCode;

    // 계약정보
    private String contractNo; // 계약번호
    private ContractStatus contractStatus; // 계약 상태
    private Integer contractPeriod; // 계약기간
    private BigDecimal totalAmount; // 총보험료

    private LocalDate contractStartDate; // 계약 시작일시
    private LocalDate contractEndDate; // 계약 종료 일시
    private LocalDateTime contracted; // 계약 일시

    // 계약 담보정보
    private List<ContractCollateralData> contractCollateralList = new ArrayList<>();

    @Builder
    public ContractResultDto(String productName, String productCode, String contractNo, ContractStatus contractStatus, Integer contractPeriod, BigDecimal totalAmount, LocalDate contractStartDate, LocalDate contractEndDate, LocalDateTime contracted, List<ContractCollateralData> contractCollateralList) {
        this.productName = productName;
        this.productCode = productCode;
        this.contractNo = contractNo;
        this.contractStatus = contractStatus;
        this.contractPeriod = contractPeriod;
        this.totalAmount = totalAmount;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contracted = contracted;
        this.contractCollateralList = contractCollateralList;
    }

    @Getter
    @NoArgsConstructor
    public static class ContractCollateralData {
        private String collateralCode;
        private String collateralName;
        private BigDecimal subscriptionAmount;
        private BigDecimal standardAmount;

        @Builder
        public ContractCollateralData(String collateralCode, String collateralName, BigDecimal subscriptionAmount, BigDecimal standardAmount) {
            this.collateralCode = collateralCode;
            this.collateralName = collateralName;
            this.subscriptionAmount = subscriptionAmount;
            this.standardAmount = standardAmount;
        }

        public static ContractCollateralData from(ContractCollateral contractCollateral) {
            return ContractCollateralData
                    .builder()
                    .collateralName(contractCollateral.getInsuranceCollateral().getName())
                    .collateralCode(contractCollateral.getInsuranceCollateral().getCode())
                    .subscriptionAmount(contractCollateral.getSubscriptionAmount())
                    .standardAmount(contractCollateral.getStandardAmount())
                    .build();
        }
    }


    public static ContractResultDto from(Contract contract) {
        InsuranceProduct insuranceProduct = contract.getInsuranceProduct();
        List<ContractCollateralData> contractCollateralDataList = contract.getContractCollaterals().stream().map(data -> ContractCollateralData.from(data)).toList();
        return ContractResultDto.builder()
                .productCode(insuranceProduct.getCode())
                .productName(insuranceProduct.getName())
                .contractNo(contract.getContractNo())
                .contractStatus(contract.getStatus())
                .contractPeriod(contract.getContractPeriod())
                .totalAmount(contract.getTotalAmount())
                .contractStartDate(contract.getStartDate())
                .contractEndDate(contract.getEndDate())
                .contracted(contract.getContracted())
                .contractCollateralList(contractCollateralDataList)
                .build();
    }
}
