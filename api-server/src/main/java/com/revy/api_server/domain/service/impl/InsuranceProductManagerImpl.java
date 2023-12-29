package com.revy.api_server.domain.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.revy.api_server.domain.entity.InsuranceProduct;
import com.revy.api_server.domain.entity.QInsuranceProduct;
import com.revy.api_server.domain.repository.InsuranceProductRepository;
import com.revy.api_server.domain.service.InsuranceProductManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Revy on 2023.12.28
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class InsuranceProductManagerImpl implements InsuranceProductManager {

    private final JPAQueryFactory jpaQueryFactory;
    private final InsuranceProductRepository repository;

    private static final QInsuranceProduct INSURANCE_PRODUCT = QInsuranceProduct.insuranceProduct;


    @Override
    public Optional<InsuranceProduct> findOneByCode(String insuranceProductCode) {
        InsuranceProduct result = jpaQueryFactory.selectFrom(INSURANCE_PRODUCT).where(INSURANCE_PRODUCT.code.eq(insuranceProductCode)).fetchFirst();
        return Optional.ofNullable(result);
    }
}
