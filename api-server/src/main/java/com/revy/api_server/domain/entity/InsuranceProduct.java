package com.revy.api_server.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Revy on 2023.12.26
 * 보험 상품 Entity
 */

@Entity
@Table(name = " insurance_product")
@Getter
@NoArgsConstructor
public class InsuranceProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 상품코드
    @Comment("상품코드")
    @Column(name = "code", unique = true, nullable = false, length = 50)
    private String code;

    // 상품명
    @Comment("상품명")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    // 최소 계약 기간
    @Comment("최소 계약 기간")
    @Column(name = "contract_period_min", nullable = false)
    private Integer contractPeriodMin;

    // 최대 계약 기간
    @Comment("최대 계약 기간")
    @Column(name = "contract_period_max", nullable = false)
    private Integer contractPeriodMax;

    @OneToMany(mappedBy = "insuranceProduct", fetch = FetchType.EAGER)
    private List<InsuranceCollateral> insuranceCollateralList = new ArrayList<>();

}
