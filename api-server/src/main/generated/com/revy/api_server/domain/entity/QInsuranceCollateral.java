package com.revy.api_server.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInsuranceCollateral is a Querydsl query type for InsuranceCollateral
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInsuranceCollateral extends EntityPathBase<InsuranceCollateral> {

    private static final long serialVersionUID = -1001904747L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInsuranceCollateral insuranceCollateral = new QInsuranceCollateral("insuranceCollateral");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInsuranceProduct insurance;

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> standardAmount = createNumber("standardAmount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> subscriptionAmount = createNumber("subscriptionAmount", java.math.BigDecimal.class);

    public QInsuranceCollateral(String variable) {
        this(InsuranceCollateral.class, forVariable(variable), INITS);
    }

    public QInsuranceCollateral(Path<? extends InsuranceCollateral> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInsuranceCollateral(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInsuranceCollateral(PathMetadata metadata, PathInits inits) {
        this(InsuranceCollateral.class, metadata, inits);
    }

    public QInsuranceCollateral(Class<? extends InsuranceCollateral> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.insurance = inits.isInitialized("insurance") ? new QInsuranceProduct(forProperty("insurance")) : null;
    }

}

