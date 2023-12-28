package com.revy.api_server.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * Created by Revy on 2023.12.26
 * 보험 계약 담보 Entity
 */

@Entity
@Table(name = "contract_collateral")
public class ContractCollateral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    // 계약
    private Contract contract;

    @ManyToOne
    // 상품정보
    private InsuranceProduct insuranceProduct;

    @ManyToOne
    // 가입담보
    private InsuranceCollateral insuranceCollateral;

    // 가입 금액
    private BigDecimal subscriptionAmount;

    // 기준 금액
    private BigDecimal standardAmount;

    // 계약 담보 금액
    private BigDecimal totalAmount;

}
