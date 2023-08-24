package com.example.demo.fernando.application.controller.mapper;

import com.example.demo.fernando.application.controller.dto.PriceDTO;
import com.example.demo.fernando.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceDtoMapper {

    PriceDtoMapper INSTANCE = Mappers.getMapper(PriceDtoMapper.class);
    PriceDTO toPriceDTO(Price price);


}