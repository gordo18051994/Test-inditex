package com.example.demo.fernando.infrastructure.db.mapper;

import com.example.demo.fernando.domain.model.Price;
import com.example.demo.fernando.infrastructure.db.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceEntityMapper {

    PriceEntityMapper INSTANCE = Mappers.getMapper(PriceEntityMapper.class);
    Price pricesEntityToPrices(PriceEntity priceEntity);

}