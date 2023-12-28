package com.revy.api_server.domain.entity;

import jakarta.persistence.*;

/**
 * Created by Revy on 2023.12.26
 * 보험 상품 Entity
 */

@Entity
@Table
public class InsuranceProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 상품명
    private String name;
    // 최소 계약 기간
    private Integer contractPeriodMin;
    // 최대 계약 기간
    private Integer contractPeriodMax;

}
