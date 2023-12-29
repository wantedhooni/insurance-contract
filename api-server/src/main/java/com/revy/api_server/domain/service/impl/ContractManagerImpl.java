package com.revy.api_server.domain.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.revy.api_server.common.enums.ContractStatus;
import com.revy.api_server.domain.entity.Contract;
import com.revy.api_server.domain.entity.QContract;
import com.revy.api_server.domain.entity.QContractCollateral;
import com.revy.api_server.domain.entity.QInsuranceProduct;
import com.revy.api_server.domain.repository.ContractRepository;
import com.revy.api_server.domain.service.ContractManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Revy on 2023.12.28
 */

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ContractManagerImpl implements ContractManager {
    private final JPAQueryFactory jpaQueryFactory;
    private final ContractRepository repository;

    private static final QContract CONTRACT = QContract.contract;
    @Override
    public Contract save(Contract contract) {
        return repository.save(contract);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contract> findOneByContractNo(String contractNo) {
        Contract result = jpaQueryFactory
                .selectFrom(CONTRACT)
                .where(CONTRACT.contractNo.eq(contractNo))
                .fetchFirst();
        return Optional.ofNullable(result);
    }
}
