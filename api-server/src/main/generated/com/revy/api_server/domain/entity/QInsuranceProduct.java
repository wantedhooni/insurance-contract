package com.revy.api_server.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInsuranceProduct is a Querydsl query type for InsuranceProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInsuranceProduct extends EntityPathBase<InsuranceProduct> {

    private static final long serialVersionUID = 1210826321L;

    public static final QInsuranceProduct insuranceProduct = new QInsuranceProduct("insuranceProduct");

    public final NumberPath<Integer> contractPeriodMax = createNumber("contractPeriodMax", Integer.class);

    public final NumberPath<Integer> contractPeriodMin = createNumber("contractPeriodMin", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QInsuranceProduct(String variable) {
        super(InsuranceProduct.class, forVariable(variable));
    }

    public QInsuranceProduct(Path<? extends InsuranceProduct> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInsuranceProduct(PathMetadata metadata) {
        super(InsuranceProduct.class, metadata);
    }

}

