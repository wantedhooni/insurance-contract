package com.revy.api_server.domain.service;

import com.revy.api_server.domain.entity.Contract;

/**
 * Created by Revy on 2023.12.28
 */
public interface ContractManager {
    Contract save(Contract contract);
}
