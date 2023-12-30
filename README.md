# 보험 계약 시스템

## 인프라 정보

---
- Swagger URL: http://localhost:8080/swagger-ui/index.html
- H2DB URL: http://localhost:8080/h2-console/
- API 정보 URL(Postman Doc): https://documenter.getpostman.com/view/31149172/2s9YsDjaQB#0b8fb3bb-176f-4e94-92c4-b493e16db9f7

## SPEC

---
- JAVA 21
- Spring Boot 3.1.5
- H2 DB
- JPA
- QueryDSL
- Swagger

## 실행 방법

---
````
# JAVA 21 버전 확인해 주세요.
git clone https://github.com/kakao-insurance-quiz/20231221-wrh.git
cd 20231221-wrh
cd api-server
gradle bootjar
java -jar ./build/libs/api-server-0.0.1.jar
````
또는
````
# JAVA 21 버전 확인해 주세요.
git clone https://github.com/kakao-insurance-quiz/20231221-wrh.git
cd 20231221-wrh
cd api-server
gradle bootrun

````

## DB 정보

---
### 테이블 정보 

---
- contract - 계약 TABLE
- contract_collateral - 계약 담보 TABLE
- insurance_product - 보험 상품 TABLE
- insurance_collateral - 보험 상품 담보 TABLE
- 컬럼 정보는 ERD를 참고하세요.

### 테이블 데이터 정보

---
![db_data.png](img%2Fdb_data.png)

### ERD 이미지

---
![erd.png](img%2Ferd.png)


## TEST CASE 결과

---
![test_case.png](img%2Ftest_case.png)


## API 명세 및 테스트

---
![api_desc.png](img%2Fapi_desc.png)

- API 정보 URL(Postman Doc): https://documenter.getpostman.com/view/31149172/2s9YsDjaQB#0b8fb3bb-176f-4e94-92c4-b493e16db9f7


