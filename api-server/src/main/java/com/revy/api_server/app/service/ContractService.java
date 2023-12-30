package com.revy.api_server.app.service;

import com.revy.api_server.app.service.dto.CalcTotalAmountParamDto;
import com.revy.api_server.app.service.dto.CalcTotalAmountResultDto;
import com.revy.api_server.app.service.dto.ContractCreateParamDto;
import com.revy.api_server.app.service.dto.ContractResultDto;

import java.util.Set;

/**
 * Created by Revy on 2023.12.28
 */
public interface ContractService {

    /**
     * 보험 계약을 생성한다.
     *
     * @param contractCreateParamDto
     * @return ContractResultDto
     */
    ContractResultDto createContract(ContractCreateParamDto contractCreateParamDto);

    /**
     * 보험 계약 정보를 조회한다.
     *
     * @param contractNo - 계약 번호
     * @return ContractResultDto
     */
    ContractResultDto retrieveContract(String contractNo);

    /**
     * 보험료를 계산한다.
     *
     * @param contractCreateParamDto
     * @return ContractCalcParamDto
     */
    CalcTotalAmountResultDto calcContract(CalcTotalAmountParamDto contractCreateParamDto);

    /**
     * 계약 담보를 추가한다.
     *
     * @param contractNo      - 계약번호
     * @param collateralCodes - 담보 코드
     */
    void addCollateral(String contractNo, Set<String> collateralCodes);

    /**
     * 계약 담보를 제거한다.
     *
     * @param contractNo      - 계약번호
     * @param collateralCodes - 담보 코드
     */
    void removeCollateral(String contractNo, Set<String> collateralCodes);

    /**
     * 계약 기간을 수정한다.
     *
     * @param contractNo     - 계약번호
     * @param contractPeriod - 계약 기간
     */
    void modifyPeriod(String contractNo, Integer contractPeriod);

    /**
     * 계약을 철회한다.
     *
     * @param contractNo       - 계약번호
     * @param withdrawalReason - 철회 사유
     */
    void withdrawal(String contractNo, String withdrawalReason);
}
