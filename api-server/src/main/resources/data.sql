INSERT INTO contract.insurance_product
    (id, code, name, contract_period_min, contract_period_max)
VALUES (1, 'FAA001', '여행자 보험', 1, 3),
       (2, 'FAO004', '휴대폰 보험', 1, 12)
;

INSERT INTO contract.insurance_collateral
(code, name, insurance_product_code, standard_amount, subscription_amount)
VALUES ('FAA001_1', '상해치료비', 'FAA001', 100, 1000000),
       ('FAA001_2', '항공기 지연도착시 보상금', 'FAA001', 100, 500000)
;

INSERT INTO contract.insurance_collateral
(code, name, insurance_product_code, standard_amount, subscription_amount)
VALUES ('FAO004_1', '부분손실', 'FAO004', 38, 750000),
       ('FAO004_2', '부분손실', 'FAO004', 40, 1570000)
;

INSERT INTO contract.contract (contract_period, end_date, start_date, total_amount, contracted, contract_no,
                               insurance_product_code, status)
VALUES (3, '2024-03-29', '2023-12-29', 30000.00, '2023-12-30 07:10:00.103306', '5efc8615-5a1d-411f-bc81-8edb4b60dacb',
        'FAA001', 'NORMAL'),
       (3, '2024-03-29', '2023-12-29', 30000.00, '2023-12-30 07:10:00.190495', '837a6831-a7d1-418d-bce1-b95352405e9f',
        'FAA001', 'NORMAL'),
       (3, '2024-03-29', '2023-12-29', 30000.00, '2023-12-30 07:10:00.59538', '819e5472-d2a6-4d4c-ab41-cac5f01ee80e',
        'FAA001', 'NORMAL'),
       (3, '2024-03-29', '2023-12-29', 30000.00, '2023-12-30 07:10:00.965039', 'e061e863-0317-4d4a-976c-d8760e559d77',
        'FAA001', 'NORMAL'),
       (3, '2024-03-29', '2023-12-29', 30000.00, '2023-12-30 07:10:01.323347', '2c3ed248-2fba-426b-8f69-5748aa1a1377',
        'FAA001', 'NORMAL'),
       (3, '2024-03-29', '2023-12-29', 30000.00, '2023-12-30 07:10:01.650709', '708943c1-2dbf-4c24-a753-53ececd4c44d',
        'FAA001', 'NORMAL');


INSERT INTO contract.contract_collateral (standard_amount, subscription_amount, contract_code,
                                          insurance_collateral_code, insurance_product_code)
VALUES (100.00, 1000000.00, '5efc8615-5a1d-411f-bc81-8edb4b60dacb', 'FAA001_1', 'FAA001'),
       (100.00, 1000000.00, '837a6831-a7d1-418d-bce1-b95352405e9f', 'FAA001_1', 'FAA001'),
       (100.00, 1000000.00, '819e5472-d2a6-4d4c-ab41-cac5f01ee80e', 'FAA001_1', 'FAA001'),
       (100.00, 1000000.00, 'e061e863-0317-4d4a-976c-d8760e559d77', 'FAA001_1', 'FAA001'),
       (100.00, 1000000.00, '2c3ed248-2fba-426b-8f69-5748aa1a1377', 'FAA001_1', 'FAA001'),
       (100.00, 1000000.00, '708943c1-2dbf-4c24-a753-53ececd4c44d', 'FAA001_1', 'FAA001');

