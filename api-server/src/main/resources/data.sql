-- 상품 DATA
INSERT INTO insurance_product
    (id, code, name, contract_period_min, contract_period_max)
VALUES (1, 'FAA001', '여행자 보험', 1, 3),
       (2, 'FAO004', '휴대폰 보험', 1, 12)
;

-- 상품 담보 - 여행자 보험 -  목록 DATA
INSERT INTO insurance_collateral
(code, name, insurance_product_code, standard_amount, subscription_amount)
VALUES ('FAA001_1', '상해치료비', 'FAA001', 100, 1000000),
       ('FAA001_2', '항공기 지연도착시 보상금', 'FAA001', 100, 500000)
;

-- 상품 담보 - 휴대폰 보험 -  목록 DATA
INSERT INTO insurance_collateral
(code, name, insurance_product_code, standard_amount, subscription_amount)
VALUES ('FAO004_1', '부분손실', 'FAO004', 38, 750000),
       ('FAO004_2', '전체손실', 'FAO004', 40, 1570000)
;

-- 계약 DATA
INSERT INTO contract (contract_period,end_date,start_date,total_amount,contracted,expired,withdrawn,contract_no,insurance_product_code,status,withdrawal_reason) VALUES
(1,'2024-01-30','2023-12-30',10000.00,'2023-12-30 17:32:05.15491',NULL,NULL,'74bbddf6-5ef8-46ef-84ea-c79b9a60a24f','FAA001','NORMAL',NULL),
(3,'2024-03-30','2023-12-30',45000.00,'2023-12-30 17:32:14.152431',NULL,NULL,'8a7f1235-dcfe-4b44-b041-d95b0d0166bf','FAA001','NORMAL',NULL),
(1,'2024-01-30','2023-12-30',19736.84,'2023-12-30 17:32:21.590543',NULL,NULL,'8b5f8772-42bd-45f9-b243-3f79e5e3b59b','FAO004','NORMAL',NULL),
(6,'2024-06-30','2023-12-30',353921.04,'2023-12-30 17:32:28.314477',NULL,NULL,'d6d729d5-bb2b-44da-8952-b33b12c3a142','FAO004','NORMAL',NULL),
(12,'2024-12-30','2023-12-30',707842.08,'2023-12-30 17:32:34.99305',NULL,NULL,'07e06095-141d-4ec9-a729-cf33f0a79670','FAO004','NORMAL',NULL),
(3,'2024-03-30','2023-12-30',45000.00,'2023-12-30 17:32:41.92376',NULL,'2023-12-30 17:33:02.234212','632770b6-1082-4bea-a30a-5a274da653ed','FAA001','WITHDRAWAL','청약철회 테스트');


-- 계약 담보 DATA
INSERT INTO contract_collateral (standard_amount,subscription_amount,contract_code,insurance_collateral_code,insurance_product_code) VALUES
(100.00,1000000.00,'74bbddf6-5ef8-46ef-84ea-c79b9a60a24f','FAA001_1','FAA001'),
(100.00,1000000.00,'8a7f1235-dcfe-4b44-b041-d95b0d0166bf','FAA001_1','FAA001'),
(100.00,500000.00,'8a7f1235-dcfe-4b44-b041-d95b0d0166bf','FAA001_2','FAA001'),
(38.00,750000.00,'8b5f8772-42bd-45f9-b243-3f79e5e3b59b','FAO004_1','FAO004'),
(40.00,1570000.00,'d6d729d5-bb2b-44da-8952-b33b12c3a142','FAO004_2','FAO004'),
(38.00,750000.00,'d6d729d5-bb2b-44da-8952-b33b12c3a142','FAO004_1','FAO004'),
(40.00,1570000.00,'07e06095-141d-4ec9-a729-cf33f0a79670','FAO004_2','FAO004'),
(38.00,750000.00,'07e06095-141d-4ec9-a729-cf33f0a79670','FAO004_1','FAO004'),
(100.00,1000000.00,'632770b6-1082-4bea-a30a-5a274da653ed','FAA001_1','FAA001'),
(100.00,500000.00,'632770b6-1082-4bea-a30a-5a274da653ed','FAA001_2','FAA001');


