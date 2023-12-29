package com.revy.api_server.app.service.impl;

import com.revy.api_server.app.service.ContractService;
import com.revy.api_server.app.service.dto.*;
import com.revy.api_server.domain.service.ContractCollateralManager;
import com.revy.api_server.domain.service.ContractManager;
import com.revy.api_server.domain.service.InsuranceCollateralManager;
import com.revy.api_server.domain.service.InsuranceProductManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Revy on 2023.12.28
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractCollateralManager contractCollateralManager;
    private final ContractManager contractManager;
    private final InsuranceCollateralManager insuranceCollateralManager;
    private final InsuranceProductManager insuranceProductManager;


    @Override
    public ContractCreateResultDto createContract(ContractCreateParamDto contractCreateParamDto) {
        return null;
    }


    @Override
    public ContractRetrieveResultDto retrieveContract(ContractRetrieveParamDto contractRetrieveParamDto) {
        return null;
    }

    @Override
    public ContractModifyResultDto modifyContract(ContractModifyParamDto contractModifyParamDto) {

        return null;
    }

    @Override
    public ContractCalcResultDto calcContract(ContractCalcParamDto contractCalcParamDto) {

        return null;
    }


}
