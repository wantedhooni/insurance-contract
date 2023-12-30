package com.revy.api_server.app.service;

import com.revy.api_server.app.service.dto.*;

import java.util.Set;

/**
 * Created by Revy on 2023.12.28
 */
public interface ContractService {

    /**
     * 보험 계약을 생성한다.
     * @param contractCreateParamDto
     * @return ContractResultDto
     */
    ContractResultDto createContract(ContractCreateParamDto contractCreateParamDto);

    /**
     * 보험 계약 정보를 조회한다.
     * @param contractNo
     * @return ContractResultDto
     */
    ContractResultDto retrieveContract(String contractNo);

    /**
     * 보험료를 계산한다.
     * @param contractCreateParamDto
     * @return ContractCalcParamDto
     */
    CalcTotalAmountResultDto calcContract(CalcTotalAmountParamDto contractCreateParamDto);

    void addCollateral(String contractNo, Set<String> collateralCodes);

    void removeCollateral(String contractNo, Set<String> collateralCodes);

    void modifyPeriod(String contractNo, Integer contractPeriod);

    void withdrawal(String contractNo, String note);
}
