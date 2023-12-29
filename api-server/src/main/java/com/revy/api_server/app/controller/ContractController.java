package com.revy.api_server.app.controller;

import com.revy.api_server.app.controller.dto.request.CalcTotalAmountReq;
import com.revy.api_server.app.controller.dto.request.CreateContractReq;
import com.revy.api_server.app.controller.dto.response.CalcTotalAmountRes;
import com.revy.api_server.app.controller.dto.response.ContractInfoRes;
import com.revy.api_server.app.service.ContractService;
import com.revy.api_server.app.service.dto.CalcTotalAmountResultDto;
import com.revy.api_server.app.service.dto.ContractResultDto;
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

/*
2. 계약정보 조회 API
계약정보를 전달받아서 해당 계약의 상세 내용을 리턴합니다.

3. 계약 정보 수정 API

해당 계약에 대해서 계약내용 변경 업무를 수행합니다.
총 보험료는 계약 생성시점에 서버에서 계산합니다.
변경 가능한 정보는 다음과 같습니다.
- 담보 추가 / 삭제
- 계약기간 변경(시작일은 변경 불가, 기간만 변경 가능)
- 계약상태 변경(단, 기간만료 상태에서는 변경 불가)

4. 예상 총 보험료 계약 API
보험 가입 전 보험료를 미리 산출해 보기 위한 기능입니다.
상품 / 담보 정보와 계약기간을 통해서 예상되는 보험료를 리턴합니다.
 */

    //

    /**
     * 예상 총 보험료 계약 API
     *
     * @param req
     * @return ContractInfoRes
     */
    @PostMapping("/calc")
    public CalcTotalAmountRes calcContract(@Valid @RequestBody CalcTotalAmountReq req) {
        CalcTotalAmountResultDto resultDto = contractService.calcContract(req.toCalcTotalAmountParamDto());
        return CalcTotalAmountRes.from(resultDto);
    }

    /**
     * 계약 생성 API
     * - 최초 계약 생성시 상태는 정상계약으로 간주합니다.
     * - 총 보험료는 계약 생성시점에 서버에서 계산합니다.
     * @param req
     * @return
     */
    @PostMapping("/create")
    public ContractInfoRes createContract(@Valid @RequestBody CreateContractReq req) {
        ContractResultDto resultDto = contractService.createContract(req.toContractCreateParamDto());
        return ContractInfoRes.from(resultDto);
    }

    // 2. 계약 정보 조회 API
    @GetMapping("/{contractNo}")
    public ContractInfoRes retrieveContract(@PathVariable("contractNo") String contractNo) {
        ContractResultDto resultDto = contractService.retrieveContract(contractNo);
        return ContractInfoRes.from(resultDto);
    }


    // 3. 계약 정보 수정 API
    @PostMapping("/modify")
    public void modifyContract() {

    }

}

