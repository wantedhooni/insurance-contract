package com.revy.api_server.app.service;

import com.revy.api_server.app.service.dto.*;

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
     * 보험 계약을 수정한다.
     * @param contractModifyParamDto
     * @return ContractModifyParamDto
     */
    ContractModifyResultDto modifyContract(ContractModifyParamDto contractModifyParamDto);

    /**
     * 보험료를 계산한다.
     * @param contractCreateParamDto
     * @return ContractCalcParamDto
     */
    CalcTotalAmountResultDto calcContract(CalcTotalAmountParamDto contractCreateParamDto);
}
