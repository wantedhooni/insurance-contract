package com.revy.api_server.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * Created by Revy on 2023.12.26
 * 보험 담보 Entity
 */

@Entity
@Table
public class InsuranceCollateral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 상위 상품
    @ManyToOne
    private InsuranceProduct insurance;

    // 담보명
    private String name;

    // 가입 금액
    private BigDecimal subscriptionAmount;

    // 기준 금액
    private BigDecimal standardAmount;

}
