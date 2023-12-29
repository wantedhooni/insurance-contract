package com.revy.api_server.app.service;

import com.revy.api_server.app.service.dto.*;

/**
 * Created by Revy on 2023.12.28
 */
public interface ContractService {

    /**
     * 보험 계약을 생성한다.
     * @param contractCreateParamDto
     * @return ContractCreateParamDto
     */
    ContractCreateResultDto createContract(ContractCreateParamDto contractCreateParamDto);

    /**
     * 보험 계약을 조회한다.
     * @param contractRetrieveParamDto
     * @return ContractRetrieveParamDto
     */
    ContractRetrieveResultDto retrieveContract(ContractRetrieveParamDto contractRetrieveParamDto);

    /**
     * 보험 계약을 수정한다.
     * @param contractModifyParamDto
     * @return ContractModifyParamDto
     */
    ContractModifyResultDto modifyContract(ContractModifyParamDto contractModifyParamDto);

    /**
     * 보험료를 계산한다.
     * @param contractCalcParamDto
     * @return ContractCalcParamDto
     */
    ContractCalcResultDto calcContract(ContractCalcParamDto contractCalcParamDto);
}
