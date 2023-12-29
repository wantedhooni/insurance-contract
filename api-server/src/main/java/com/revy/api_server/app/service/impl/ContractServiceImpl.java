package com.revy.api_server.app.service.impl;

import com.revy.api_server.app.service.ContractService;
import com.revy.api_server.app.service.dto.*;
import com.revy.api_server.common.enums.ContractStatus;
import com.revy.api_server.common.enums.ErrorCode;
import com.revy.api_server.common.exceptions.NotFoundException;
import com.revy.api_server.domain.entity.Contract;
import com.revy.api_server.domain.entity.ContractCollateral;
import com.revy.api_server.domain.entity.InsuranceCollateral;
import com.revy.api_server.domain.entity.InsuranceProduct;
import com.revy.api_server.domain.service.ContractManager;
import com.revy.api_server.domain.service.InsuranceProductManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Revy on 2023.12.28
 */

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ContractServiceImpl implements ContractService {

    private final ContractManager contractManager;
    private final InsuranceProductManager insuranceProductManager;


    @Override
    public ContractResultDto createContract(ContractCreateParamDto contractCreateParamDto) {
        log.debug("{}", contractCreateParamDto);

        Assert.notNull(contractCreateParamDto, "contractCreateParamDto must not be null.");
        Assert.notEmpty(contractCreateParamDto.getCollateralCodes(), "contractCreateParamDto.getCollateralCodes() must not be empty.");
        Assert.notNull(contractCreateParamDto.getContractPeriod(), "contractCreateParamDto.getContractPeriod() must not be null.");
        Assert.notNull(contractCreateParamDto.getStartDate(), "contractCreateParamDto.getStartDate() must not be null.");

        // 상품 코드 Valid
        InsuranceProduct insuranceProduct = insuranceProductManager.findOneByCode(contractCreateParamDto.getProductCode())
                .orElseThrow(() -> new NotFoundException(ErrorCode.BAD_REQUEST, "[%s] 코드의 상품을 찾을수 없습니다.".formatted(contractCreateParamDto.getProductCode())));

        // 담보 코드 Valid
        Map<String, InsuranceCollateral> insuranceCollateralMap = insuranceProduct.getInsuranceCollateralList().stream().collect(Collectors.toMap(
                data -> data.getCode(), Function.identity()));

        List<InsuranceCollateral> requestCollateralList = contractCreateParamDto.getCollateralCodes().stream().map(
                requestCollateralCode -> {
                    if (!insuranceCollateralMap.containsKey(requestCollateralCode)) {
                        throw new NotFoundException(ErrorCode.BAD_REQUEST, "상품 코드[%s]의 담보코드[%s]를 찾을수 없습니다.".formatted(contractCreateParamDto.getProductCode(), requestCollateralCode));
                    }
                    return insuranceCollateralMap.get(requestCollateralCode);
                }
        ).toList();

        // 기간 최소값 Valid
        if (insuranceProduct.getContractPeriodMin() > contractCreateParamDto.getContractPeriod()) {
            throw new NotFoundException(ErrorCode.BAD_REQUEST, "상품 코드[%s]의 최소 기간은 %s 입니다. 요청값: %s".formatted(contractCreateParamDto.getProductCode(), insuranceProduct.getContractPeriodMin(), contractCreateParamDto.getContractPeriod()));
        }

        // 기간 최대값 Valid
        if (insuranceProduct.getContractPeriodMax() < contractCreateParamDto.getContractPeriod()) {
            throw new NotFoundException(ErrorCode.BAD_REQUEST, "상품 코드[%s]의 최대 기간은 %s 입니다. 요청값: %s".formatted(contractCreateParamDto.getProductCode(), insuranceProduct.getContractPeriodMax(), contractCreateParamDto.getContractPeriod()));
        }

        // 시작일 Check
        LocalDate now = LocalDate.now();
        LocalDate endDate = contractCreateParamDto.getStartDate().plusMonths(contractCreateParamDto.getContractPeriod());
        log.debug("now:{}, startDate:{}, endDate:{}", now, contractCreateParamDto.getStartDate(), endDate);

        // TODO:LEVI - 계약시작일이 과거 일수 있는지는 아직 잘 모르겠음. 우선 계약 시작일이 현재보다 과거이면 막는다.
        if (now.isBefore(contractCreateParamDto.getStartDate())) {
            throw new NotFoundException(ErrorCode.BAD_REQUEST, "계약 시작일은 금일보다 과거일 수 없습니다.. 요청값: %s".formatted(contractCreateParamDto.getStartDate()));
        }

        // 총보험료 계산
        BigDecimal totalAmount = requestCollateralList.stream()
                .map(insuranceCollateral -> insuranceCollateral.getSubscriptionAmount().divide(insuranceCollateral.getStandardAmount(), 2, RoundingMode.DOWN))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(new BigDecimal(contractCreateParamDto.getContractPeriod()))
                .setScale(2, RoundingMode.DOWN);
        log.debug("totalAmount: {}", totalAmount);

        Contract requestContract = createNewContract(insuranceProduct
                , contractCreateParamDto.getContractPeriod()
                , contractCreateParamDto.getStartDate()
                , endDate
                , totalAmount);

        contractManager.save(requestContract);

        List<ContractCollateral> requestContractCollateralList = requestCollateralList.stream().map(data ->
                createNewContractCollateral(requestContract, data)
        ).toList();
        requestContract.setContractCollaterals(requestContractCollateralList);

        return ContractResultDto.from(requestContract);
    }


    @Override
    @Transactional(readOnly = true)
    public ContractResultDto retrieveContract(String contractNo) {
        Contract requestContract = contractManager.findOneByContractNo(contractNo)
                .orElseThrow(() -> new NotFoundException(ErrorCode.BAD_REQUEST, "[%s] 계약번호의 계약이 존재하지 않습니다.".formatted(contractNo)));
        return ContractResultDto.from(requestContract);
    }

    @Override
    public ContractModifyResultDto modifyContract(ContractModifyParamDto contractModifyParamDto) {

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public CalcTotalAmountResultDto calcContract(CalcTotalAmountParamDto calcTotalAmountParamDto) {
        log.debug("{}", calcTotalAmountParamDto);

        Assert.notNull(calcTotalAmountParamDto, "calcTotalAmountParamDto must not be null.");
        Assert.notEmpty(calcTotalAmountParamDto.getCollateralCodes(), "calcTotalAmountParamDto.getCollateralCodes() must not be empty.");
        Assert.notNull(calcTotalAmountParamDto.getContractPeriod(), "calcTotalAmountParamDto.getContractPeriod() must not be null.");
        Assert.notNull(calcTotalAmountParamDto.getStartDate(), "calcTotalAmountParamDto.getStartDate() must not be null.");

        // 상품 코드 Valid
        InsuranceProduct insuranceProduct = insuranceProductManager.findOneByCode(calcTotalAmountParamDto.getProductCode())
                .orElseThrow(() -> new NotFoundException(ErrorCode.BAD_REQUEST, "[%s] 코드의 상품을 찾을수 없습니다.".formatted(calcTotalAmountParamDto.getProductCode())));

        // 담보 코드 Valid
        Map<String, InsuranceCollateral> insuranceCollateralMap = insuranceProduct.getInsuranceCollateralList()
                .stream()
                .collect(Collectors.toMap(InsuranceCollateral::getCode, Function.identity()));


        List<InsuranceCollateral> requestCollateralList = calcTotalAmountParamDto.getCollateralCodes().stream().map(
                requestCollateralCode -> {
                    if (!insuranceCollateralMap.containsKey(requestCollateralCode)) {
                        throw new NotFoundException(ErrorCode.BAD_REQUEST, "상품 코드[%s]의 담보코드[%s]를 찾을수 없습니다.".formatted(calcTotalAmountParamDto.getProductCode(), requestCollateralCode));
                    }
                    return insuranceCollateralMap.get(requestCollateralCode);
                }
        ).toList();

        // 기간 최소값 Valid
        if (insuranceProduct.getContractPeriodMin() > calcTotalAmountParamDto.getContractPeriod()) {
            throw new NotFoundException(ErrorCode.BAD_REQUEST, "상품 코드[%s]의 최소 기간은 %s 입니다. 요청값: %s".formatted(calcTotalAmountParamDto.getProductCode(), insuranceProduct.getContractPeriodMin(), calcTotalAmountParamDto.getContractPeriod()));
        }

        // 기간 최대값 Valid
        if (insuranceProduct.getContractPeriodMax() < calcTotalAmountParamDto.getContractPeriod()) {
            throw new NotFoundException(ErrorCode.BAD_REQUEST, "상품 코드[%s]의 최대 기간은 %s 입니다. 요청값: %s".formatted(calcTotalAmountParamDto.getProductCode(), insuranceProduct.getContractPeriodMax(), calcTotalAmountParamDto.getContractPeriod()));
        }

        // 시작일 Check
        LocalDate now = LocalDate.now();
        LocalDate endDate = calcTotalAmountParamDto.getStartDate().plusMonths(calcTotalAmountParamDto.getContractPeriod());
        log.debug("now:{}, startDate:{}, endDate:{}", now, calcTotalAmountParamDto.getStartDate(), endDate);

        // TODO:LEVI - 계약시작일이 과거 일수 있는지는 아직 잘 모르겠음. 우선 계약 시작일이 현재보다 과거이면 막는다.
        if (now.isBefore(calcTotalAmountParamDto.getStartDate())) {
            throw new NotFoundException(ErrorCode.BAD_REQUEST, "계약 시작일은 금일보다 과거일 수 없습니다.. 요청값: %s".formatted(calcTotalAmountParamDto.getStartDate()));
        }

        // 총보험료 계산
        BigDecimal totalAmount = requestCollateralList.stream()
                .map(insuranceCollateral -> insuranceCollateral.getSubscriptionAmount().divide(insuranceCollateral.getStandardAmount(), 2, RoundingMode.DOWN))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(new BigDecimal(calcTotalAmountParamDto.getContractPeriod()))
                .setScale(2, RoundingMode.DOWN);
        log.debug("totalAmount: {}", totalAmount);

        return CalcTotalAmountResultDto.builder()
                .productName(insuranceProduct.getName())
                .productCode(insuranceProduct.getCode())
                .totalAmount(totalAmount)
                .contractPeriod(calcTotalAmountParamDto.getContractPeriod())
                .contractStartDate(calcTotalAmountParamDto.getStartDate())
                .contractEndDate(endDate)
                .contractCollateralList(requestCollateralList.stream().map(data -> CalcTotalAmountResultDto.ContractCollateralData.from(data)).toList())
                .build();
    }


    private Contract createNewContract(InsuranceProduct insuranceProduct, Integer contractPeriod, LocalDate startDate, LocalDate endDate, BigDecimal totalAmount) {
        String contractNo = "%s_%s".formatted(insuranceProduct.getCode(), UUID.randomUUID().toString());
        return Contract.builder()
                .contractNo(contractNo)
                .status(ContractStatus.NORMAL)
                .contracted(LocalDateTime.now())
                .insuranceProduct(insuranceProduct)
                .contractPeriod(contractPeriod)
                .startDate(startDate)
                .endDate(endDate)
                .totalAmount(totalAmount)
                .build();
    }


    private ContractCollateral createNewContractCollateral(Contract contract, InsuranceCollateral insuranceCollateral) {
        return ContractCollateral.builder()
                .contract(contract)
                .insuranceCollateral(insuranceCollateral)
                .insuranceProduct(insuranceCollateral.getInsuranceProduct())
                .subscriptionAmount(insuranceCollateral.getSubscriptionAmount())
                .standardAmount(insuranceCollateral.getStandardAmount())
                .build();
    }
}
