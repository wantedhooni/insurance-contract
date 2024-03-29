package com.revy.api_server.domain.service;

import com.revy.api_server.domain.entity.ContractCollateral;

import java.util.List;

/**
 * Created by Revy on 2023.12.28
 */
public interface ContractCollateralManager {


    List<ContractCollateral> saveAll(List<ContractCollateral> contractCollateralList);

    void delete(ContractCollateral contractCollateral);
}
