package com.revy.api_server.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

/**
 * Created by Revy on 2023.12.26
 * 보험 계약 담보 Entity
 */

@Entity
@Table
@Getter
@NoArgsConstructor
public class ContractCollateral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("계약 Code")
    @ManyToOne
    @JoinColumn(name = "contract_code", referencedColumnName = "contract_no", nullable = false)
    private Contract contract;


    @Comment("상품 Code")
    @ManyToOne
    @JoinColumn(name = "insurance_product_code", referencedColumnName = "code", nullable = false)
    private InsuranceProduct insuranceProduct;

    @Comment("가입 담보 Code")
    @ManyToOne
    @JoinColumn(name = "insurance_collateral_code", referencedColumnName = "code", nullable = false)
    private InsuranceCollateral insuranceCollateral;


    @Comment("가입 금액")
    private BigDecimal subscriptionAmount;

    // 기준 금액
    @Comment("기준 금액")
    private BigDecimal standardAmount;

    @Builder
    public ContractCollateral(Contract contract, InsuranceProduct insuranceProduct, InsuranceCollateral insuranceCollateral, BigDecimal subscriptionAmount, BigDecimal standardAmount) {
        this.contract = contract;
        this.insuranceProduct = insuranceProduct;
        this.insuranceCollateral = insuranceCollateral;
        this.subscriptionAmount = subscriptionAmount;
        this.standardAmount = standardAmount;
    }
}
