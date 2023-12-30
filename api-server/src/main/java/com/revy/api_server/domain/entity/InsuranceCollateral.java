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
@Table(name = "insurance_collateral")
@Getter
@NoArgsConstructor
public class InsuranceCollateral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Comment("상품 코드")
    @JoinColumn(name = "insurance_product_code", referencedColumnName = "code", nullable = false)
    private InsuranceProduct insuranceProduct;

    @Comment("담보 코드")
    @Column(name = "code", unique = true, nullable = false, length = 50)
    private String code;

    @Comment("담보명")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Comment("가입 금액")
    @Column(name = "subscription_amount", nullable = false, scale = 2)
    private BigDecimal subscriptionAmount;

    @Comment("기준 금액")
    @Column(name = "standard_amount", nullable = false, scale = 2)
    private BigDecimal standardAmount;

}
