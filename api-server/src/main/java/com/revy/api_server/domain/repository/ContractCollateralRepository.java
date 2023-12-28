package com.revy.api_server.domain.repository;

import com.revy.api_server.domain.entity.ContractCollateral;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Revy on 2023.12.27
 */
public interface ContractCollateralRepository extends JpaRepository<ContractCollateral, Long> {
}
