package com.revy.api_server.domain.service.impl;

import com.revy.api_server.domain.repository.ContractCollateralRepository;
import com.revy.api_server.domain.service.ContractCollateralManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Revy on 2023.12.28
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractCollateralManagerImpl implements ContractCollateralManager {
    private final ContractCollateralRepository repository;
}
