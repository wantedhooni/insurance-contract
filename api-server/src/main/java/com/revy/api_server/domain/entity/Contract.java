package com.revy.api_server.domain.entity;

import com.revy.api_server.common.enums.ContractStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Revy on 2023.12.26
 * 보험 계약 Entity
 */

@Entity
@Table
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 계약번호
    private String contractNo;

    @Enumerated(EnumType.STRING)
    // 계약 상태 (정상계약 / 청약철회 / 기간만료)
    private ContractStatus status;

    // 상품정보

    @ManyToOne
    private InsuranceProduct insuranceProduct;

    @OneToMany(mappedBy = "contract")
    // 가입담보 List
    private List<ContractCollateral> contractCollaterals = new ArrayList<>();

    // 보험시작일
    private LocalDate startDate;

    // 보험 종료일
    private LocalDate endDate;
    // 총 보험료
    private BigDecimal totalAmount;





}
