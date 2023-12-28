package com.revy.api_server.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContractCollateral is a Querydsl query type for ContractCollateral
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContractCollateral extends EntityPathBase<ContractCollateral> {

    private static final long serialVersionUID = -575669371L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContractCollateral contractCollateral = new QContractCollateral("contractCollateral");

    public final QContract contract;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInsuranceCollateral insuranceCollateral;

    public final QInsuranceProduct insuranceProduct;

    public final NumberPath<java.math.BigDecimal> standardAmount = createNumber("standardAmount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> subscriptionAmount = createNumber("subscriptionAmount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> totalAmount = createNumber("totalAmount", java.math.BigDecimal.class);

    public QContractCollateral(String variable) {
        this(ContractCollateral.class, forVariable(variable), INITS);
    }

    public QContractCollateral(Path<? extends ContractCollateral> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContractCollateral(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContractCollateral(PathMetadata metadata, PathInits inits) {
        this(ContractCollateral.class, metadata, inits);
    }

    public QContractCollateral(Class<? extends ContractCollateral> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contract = inits.isInitialized("contract") ? new QContract(forProperty("contract"), inits.get("contract")) : null;
        this.insuranceCollateral = inits.isInitialized("insuranceCollateral") ? new QInsuranceCollateral(forProperty("insuranceCollateral"), inits.get("insuranceCollateral")) : null;
        this.insuranceProduct = inits.isInitialized("insuranceProduct") ? new QInsuranceProduct(forProperty("insuranceProduct")) : null;
    }

}

