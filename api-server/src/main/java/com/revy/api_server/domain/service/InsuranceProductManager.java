package com.revy.api_server.domain.service;

import com.revy.api_server.domain.entity.InsuranceProduct;

import java.util.Optional;

/**
 * Created by Revy on 2023.12.28
 */
public interface InsuranceProductManager {
    Optional<InsuranceProduct> findOneByCode(String insuranceProductCode);
}
