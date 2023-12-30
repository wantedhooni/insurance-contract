package com.revy.api_server.domain.entity;

import com.revy.api_server.common.constants.CommonConstant;
import com.revy.api_server.common.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    // 계약 일시
    @Column(name = "contracted")
    private LocalDateTime contracted;

    // 청약 철회 일시
    @Column(name = "withdrawn")
    private LocalDateTime withdrawn;

    @OneToMany(mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    // 가입담보 List
    private List<ContractCollateral> contractCollaterals = new ArrayList<>();

    @Builder
    public Contract(String contractNo, ContractStatus status, InsuranceProduct insuranceProduct, Integer contractPeriod, LocalDate startDate, LocalDate endDate, BigDecimal totalAmount, LocalDateTime contracted, List<ContractCollateral> contractCollaterals) {
        this.contractNo = contractNo;
        this.status = status;
        this.insuranceProduct = insuranceProduct;
        this.contractPeriod = contractPeriod;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
        this.contracted = contracted;
        this.contractCollaterals = contractCollaterals;
    }

    public void updateContractCollaterals(List<ContractCollateral> contractCollaterals) {
        this.contractCollaterals = contractCollaterals;
    }


    public void updateTotalAmount() {
        this.totalAmount = contractCollaterals.stream()
                .map(insuranceCollateral -> insuranceCollateral.getSubscriptionAmount().divide(insuranceCollateral.getStandardAmount(), CommonConstant.DEFAULT_SCALE, RoundingMode.DOWN))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(new BigDecimal(contractPeriod))
                .setScale(CommonConstant.DEFAULT_SCALE, RoundingMode.DOWN);
    }

    public void updatePeriod(Integer contractPeriod) {
        this.contractPeriod = contractPeriod;
        this.endDate = startDate.plusMonths(contractPeriod);
        updateTotalAmount();
    }

    public void withdraw() {
        this.status = ContractStatus.WITHDRAWAL;
        this.withdrawn = LocalDateTime.now();
    }
}
