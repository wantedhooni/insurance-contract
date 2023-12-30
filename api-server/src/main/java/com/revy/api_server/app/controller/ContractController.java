package com.revy.api_server.app.controller;

import com.revy.api_server.app.controller.dto.request.*;
import com.revy.api_server.app.controller.dto.response.CalcTotalAmountRes;
import com.revy.api_server.app.controller.dto.response.ContractInfoRes;
import com.revy.api_server.app.service.ContractService;
import com.revy.api_server.app.service.dto.CalcTotalAmountResultDto;
import com.revy.api_server.app.service.dto.ContractResultDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Revy on 2023.12.26
 */

@RestController
@RequestMapping("/api/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    /**
     * 예상 총 보험료 계약 API
     *
     * @param req
     * @return ContractInfoRes
     */
    @PostMapping("/calc")
    @Operation(description = "예상 총 보험료 계약 API")
    public CalcTotalAmountRes calcContract(@Valid @RequestBody CalcTotalAmountReq req) {
        CalcTotalAmountResultDto resultDto = contractService.calcContract(req.toCalcTotalAmountParamDto());
        return CalcTotalAmountRes.from(resultDto);
    }

    /**
     * 계약 생성 API
     * - 최초 계약 생성시 상태는 정상계약으로 간주합니다.
     * - 총 보험료는 계약 생성시점에 서버에서 계산합니다.
     *
     * @param req
     * @return
     */
    @PostMapping("/create")
    @Operation(description = "계약 생성 API")
    public ContractInfoRes createContract(@Valid @RequestBody ContractCreateReq req) {
        ContractResultDto resultDto = contractService.createContract(req.toContractCreateParamDto());
        return ContractInfoRes.from(resultDto);
    }

    /**
     * 계약 정보 상세 조회 API
     *
     * @param contractNo
     * @return
     */
    @GetMapping("/{contractNo}")
    @Operation(description = "계약 정보 상세 조회 API")
    public ContractInfoRes retrieveContract(@PathVariable("contractNo") String contractNo) {
        ContractResultDto resultDto = contractService.retrieveContract(contractNo);
        return ContractInfoRes.from(resultDto);
    }

    /**
     * 계약 담보 추가 API
     */
    @PostMapping("/{contractNo}/collateral/add")
    @Operation(description = "계약 담보 추가 API")
    public ContractInfoRes addCollateral(@PathVariable("contractNo") String contractNo,
                                         @Valid @RequestBody CollateralAddReq req) {
        contractService.addCollateral(contractNo, req.getCollateralCodes());
        ContractResultDto resultDto = contractService.retrieveContract(contractNo);

        return ContractInfoRes.from(resultDto);
    }


    /**
     * 계약 담보 제거 API
     */
    @PostMapping("/{contractNo}/collateral/remove")
    @Operation(description = "계약 담보 제거 API")
    public ContractInfoRes removeCollateral(@PathVariable("contractNo") String contractNo,
                                            @Valid @RequestBody CollateralRemoveReq req) {
        contractService.removeCollateral(contractNo, req.getCollateralCodes());
        ContractResultDto resultDto = contractService.retrieveContract(contractNo);
        return ContractInfoRes.from(resultDto);
    }

    /**
     * 계약기간 변경 API
     *
     * @param contractNo
     * @param req
     * @return
     */
    @PostMapping("/{contractNo}/modify/period")
    @Operation(description = "계약기간 변경 API")
    public ContractInfoRes updateContractContractPeriod(@PathVariable("contractNo") String contractNo,
                                                        @Valid @RequestBody ContractModifyPeriodReq req) {
        contractService.modifyPeriod(contractNo, req.getContractPeriod());
        ContractResultDto resultDto = contractService.retrieveContract(contractNo);
        return ContractInfoRes.from(resultDto);
    }

    /**
     * 계약 청약철회 API
     *
     * @param contractNo
     * @param req
     * @return
     */
    @PostMapping("/{contractNo}/withdrawal")
    @Operation(description = "계약 청약철회 API")
    public ContractInfoRes withdrawalContract(@PathVariable("contractNo") String contractNo,
                                              @Valid @RequestBody ContractWithdrawalReq req) {
        contractService.withdrawal(contractNo, req.getNote());
        ContractResultDto resultDto = contractService.retrieveContract(contractNo);
        return ContractInfoRes.from(resultDto);
    }

}

