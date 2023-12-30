package com.revy.api_server.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revy.api_server.app.controller.dto.request.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Revy on 2023.12.30
 */
@SpringBootTest
@AutoConfigureMockMvc
class ContractControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    protected static final String URL_GET_CONTRACT = "/api/contract/{contractNo}";
    protected static final String URL_POST_CONTRACT_CALC = "/api/contract/calc";
    protected static final String URL_POST_CONTRACT_CREATE = "/api/contract/create";
    protected static final String URL_POST_COLLATERAL_ADD = "/api/contract/{contractNo}/collateral/add";
    protected static final String URL_POST_COLLATERAL_REMOVE = "/api/contract/{contractNo}/collateral/remove";

    protected static final String URL_POST_CONTRACT_MODIFY_PERIOD = "/api/contract/{contractNo}/modify/period";

    protected static final String URL_POST_CONTRACT_WITHDRAWAL = "/api/contract/{contractNo}/withdrawal";


    @BeforeEach
    void setUp() {
    }

    @Nested
    class 예상_보험금_계산_API {
        @Test
        void 예상_보험금_계산_성공() throws Exception {
            CalcTotalAmountReq req = CalcTotalAmountReq
                    .builder()
                    .productCode("FAA001")
                    .collateralCodes(Set.of("FAA001_1"))
                    .contractPeriod(1)
                    .startDate(LocalDate.now())
                    .build();

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_CONTRACT_CALC)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk()).andReturn();

        }

        @Test
        void 예상_보험금_계산_상품코드_NULL_실패() throws Exception {
            CalcTotalAmountReq req = CalcTotalAmountReq
                    .builder()
//                .productCode("FAA001")
                    .collateralCodes(Set.of("FAA001_1"))
                    .contractPeriod(1)
                    .startDate(LocalDate.now())
                    .build();

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_CONTRACT_CALC)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is4xxClientError()).andReturn();
        }

    }


    @Nested
    class 계약_생성_API {
        @Test
        void 계약_생성_성공() throws Exception {
            ContractCreateReq req = ContractCreateReq
                    .builder()
                    .productCode("FAA001")
                    .collateralCodes(Set.of("FAA001_1", "FAA001_2"))
                    .contractPeriod(1)
                    .startDate(LocalDate.now())
                    .build();

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_CONTRACT_CREATE)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk()).andReturn();
        }


        @Test
        void 계약_생성_담보_NULL_실패() throws Exception {
            ContractCreateReq req = ContractCreateReq
                    .builder()
                    .productCode("FAA001")
//                .collateralCodes(Set.of("FAA001_1", "FAA001_2"))
                    .contractPeriod(1)
                    .startDate(LocalDate.now())
                    .build();

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_CONTRACT_CREATE)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is4xxClientError())
                    .andReturn();
        }
    }


    @Nested
    class 계약_조회_API {
        @Test
        void 계약_조회_성공() throws Exception {
            MvcResult result = mockMvc.perform(
                            get(URL_GET_CONTRACT, "74bbddf6-5ef8-46ef-84ea-c79b9a60a24f")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
        }

        @Test
        void 계약_조회_존재하지않음_살퍄() throws Exception {
            MvcResult result = mockMvc.perform(
                            get(URL_GET_CONTRACT, UUID.randomUUID().toString())
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is4xxClientError())
                    .andReturn();
        }

    }

    @Nested
    class 계약_수정_담보_추가_API {
        @Test
        void 계약_수정_담보_추가_성공() throws Exception {
            String targetContractNo = "74bbddf6-5ef8-46ef-84ea-c79b9a60a24f";
            CollateralAddReq req = new CollateralAddReq(Set.of("FAA001_2"));

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_COLLATERAL_ADD, targetContractNo)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
        }

        @Test
        void 계약_수정_담보_추가_이미존재담보_실패() throws Exception {
            String targetContractNo = "8b5f8772-42bd-45f9-b243-3f79e5e3b59b";
            CollateralAddReq req = new CollateralAddReq(Set.of("FAA001_1"));

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_COLLATERAL_ADD, targetContractNo)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is4xxClientError())
                    .andReturn();
        }

        @Test
        void 계약_수정_담보_추가_타상품담보_실패() throws Exception {
            String targetContractNo = "8b5f8772-42bd-45f9-b243-3f79e5e3b59b";
            CollateralAddReq req = new CollateralAddReq(Set.of("FAO004_1"));

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_COLLATERAL_ADD, targetContractNo)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is4xxClientError())
                    .andReturn();
        }

        @Test
        void 계약_수정_담보_추가_미존재담보코드_실패() throws Exception {
            String targetContractNo = "8b5f8772-42bd-45f9-b243-3f79e5e3b59b";
            CollateralAddReq req = new CollateralAddReq(Set.of("TEST_1"));

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_COLLATERAL_ADD, targetContractNo)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is4xxClientError())
                    .andReturn();
        }
    }


    @Nested
    class 계약_수정_제거_추가_API {
        @Test
        void 계약_수정_담보_제거_성공() throws Exception {
            String targetContractNo = "8b5f8772-42bd-45f9-b243-3f79e5e3b59b";
            CollateralAddReq req = new CollateralAddReq(Set.of("FAA001_2"));

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_COLLATERAL_REMOVE, targetContractNo)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
        }


        @Test
        void 계약_수정_담보_제거_담보_완전제거_실패() throws Exception {
            String targetContractNo = "8a7f1235-dcfe-4b44-b041-d95b0d0166bf";
            CollateralAddReq req = new CollateralAddReq(Set.of("FAA001_1", "FAA001_2"));

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_COLLATERAL_REMOVE, targetContractNo)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is4xxClientError())
                    .andReturn();
        }
    }

    @Nested
    class 계약_수정_기간_수정_API {

        @Test
        void 계약_수정_기간_수정_성공() throws Exception {
            String targetContractNo = "07e06095-141d-4ec9-a729-cf33f0a79670";
            ContractModifyPeriodReq req = new ContractModifyPeriodReq(1);

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_CONTRACT_MODIFY_PERIOD, targetContractNo)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
        }

        @Test
        void 계약_수정_기간_수정_현재값과_동일_실패() throws Exception {
            String targetContractNo = "8a7f1235-dcfe-4b44-b041-d95b0d0166bf";
            ContractModifyPeriodReq req = new ContractModifyPeriodReq(3);

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_CONTRACT_MODIFY_PERIOD, targetContractNo)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is4xxClientError())
                    .andReturn();
        }

        @Test
        void 계약_수정_기간_수정_상품_MAX_값_오버_실패() throws Exception {
            String targetContractNo = "8a7f1235-dcfe-4b44-b041-d95b0d0166bf";
            ContractModifyPeriodReq req = new ContractModifyPeriodReq(24);

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_CONTRACT_MODIFY_PERIOD, targetContractNo)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is4xxClientError())
                    .andReturn();
        }
    }


    @Nested
    class 계약_수정_청약철회_API {
        @Test
        void 계약_수정_청약철회_성공() throws Exception {
            String targetContractNo = "8a7f1235-dcfe-4b44-b041-d95b0d0166bf";
            ContractWithdrawalReq req = new ContractWithdrawalReq("철회사유: 개인변심");

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_CONTRACT_WITHDRAWAL, targetContractNo)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
        }

        @Test
        void 계약_수정_청약철회_이미철회상품_실패() throws Exception {
            String targetContractNo = "632770b6-1082-4bea-a30a-5a274da653ed";
            ContractWithdrawalReq req = new ContractWithdrawalReq("철회사유: 개인변심");

            String jsonRequest = objectMapper.writeValueAsString(req);
            MvcResult result = mockMvc.perform(
                            post(URL_POST_CONTRACT_WITHDRAWAL, targetContractNo)
                                    .content(jsonRequest)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is4xxClientError())
                    .andReturn();
        }
    }


}