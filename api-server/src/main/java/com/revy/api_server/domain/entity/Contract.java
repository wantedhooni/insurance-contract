package com.revy.api_server.domain.entity;

import com.revy.api_server.common.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 계약 번호
    @Column(name = "contract_no", unique = true, nullable = false)
    private String contractNo;

    @Enumerated(EnumType.STRING)
    // 계약 상태 (정상계약 / 청약철회 / 기간만료)
    @Column(name = "status", nullable = false)
    private ContractStatus status;

    // 상품정보
    @ManyToOne
    @JoinColumn(name = "insurance_product_code", referencedColumnName = "code", nullable = false)
    private InsuranceProduct insuranceProduct;

    // 계약기간
    @Column(name = "contract_period", nullable = false)
    private Integer contractPeriod;

    // 보험 시작일
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    // 보험 종료일
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    // 총 보험료
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // 가입담보 List
    private List<ContractCollateral> contractCollaterals = new ArrayList<>();

    @Builder
    public Contract(String contractNo, ContractStatus status, InsuranceProduct insuranceProduct, List<ContractCollateral> contractCollaterals, Integer contractPeriod, LocalDate startDate, LocalDate endDate, BigDecimal totalAmount) {
        this.contractNo = contractNo;
        this.status = status;
        this.insuranceProduct = insuranceProduct;
        this.contractCollaterals = contractCollaterals;
        this.contractPeriod = contractPeriod;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
    }


    public void setContractCollaterals(List<ContractCollateral> contractCollaterals) {
        this.contractCollaterals = contractCollaterals;
    }
}
