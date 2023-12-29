INSERT INTO contract.insurance_product
(id, code, name, contract_period_min, contract_period_max)
VALUES
    (1, 'FAA001', '여행자 보험', 1, 3),
    (2, 'FAO004', '휴대폰 보험', 1, 12)
;

INSERT INTO contract.insurance_collateral
(code, name, insurance_product_code, standard_amount, subscription_amount)
VALUES
    ('FAA001_1', '상해치료비', 'FAA001',  100, 1000000),
    ('FAA001_2', '항공기 지연도착시 보상금', 'FAA001',  100, 500000)
;

INSERT INTO contract.insurance_collateral
(code, name, insurance_product_code, standard_amount, subscription_amount)
VALUES
    ('FAO004_1', '부분손실', 'FAO004',  38, 750000),
    ('FAO004_2', '부분손실', 'FAO004',  40, 1570000)
;