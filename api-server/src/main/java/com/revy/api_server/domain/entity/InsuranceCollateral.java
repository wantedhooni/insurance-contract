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

    @Comment("담보 코드")
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @ManyToOne
    @Comment("상품 코드")
    @JoinColumn(name = "insurance_product_code", referencedColumnName = "code")
    private InsuranceProduct insuranceProduct;


    @Comment("담보명")
    @Column(name = "name", nullable = false)
    private String name;

    @Comment("가입 금액")
    @Column(name = "subscription_amount", nullable = false, scale = 3)
    private BigDecimal subscriptionAmount;

    @Comment("기준 금액")
    @Column(name = "standard_amount", nullable = false, scale = 3)
    private BigDecimal standardAmount;

}
