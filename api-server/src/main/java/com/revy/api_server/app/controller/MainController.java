package com.revy.api_server.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Revy on 2023.12.26
 */

@RestController
@RequestMapping("/api/contract")
public class MainController {

/*
1. 계약 생성 API
최초 계약 생성시 상태는 정상계약으로 간주합니다.
총 보험료는 계약 생성시점에 서버에서 계산합니다.

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


    // 1. 계약 생성 API
    @PostMapping("/create")
    public void createContract() {

    }

    // 2. 계약 정보 조회 API
    @GetMapping
    public void getContract() {

    }

//    @PostMapping("/modify")
    // 3. 계약 정보 수정 API
    public void modifyContract() {

    }

//    @PostMapping("/calc")
    //4. 예상 총 보험료 계약 API
    public void calcContract() {

    }
}

