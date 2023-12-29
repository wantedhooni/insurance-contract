package com.revy.api_server.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

/**
 * Created by Revy on 2023.12.26
 * 보험 담보 Entity
 */

@Entity
@Table
@Getter
@NoArgsConstructor
public class InsuranceCollateral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 담보 코드
    @Comment("담보 코드")
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    // 상위 상품
    @ManyToOne
    @Comment("상품 코드")
    @JoinColumn(name = "insurance_product_code", referencedColumnName = "code")
    private InsuranceProduct insuranceProduct;

    // 담보명
    @Comment("담보명")
    @Column(name = "name", nullable = false)
    private String name;

    // 가입 금액
    @Comment("가입 금액")
    @Column(name = "subscription_amount", nullable = false, scale = 3)
    private BigDecimal subscriptionAmount;

    // 기준 금액
    @Comment("기준 금액")
    @Column(name = "standard_amount", nullable = false, scale = 3)
    private BigDecimal standardAmount;

}
