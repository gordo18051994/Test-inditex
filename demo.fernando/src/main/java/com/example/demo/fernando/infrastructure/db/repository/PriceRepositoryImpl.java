package com.example.demo.fernando.infrastructure.db.repository;

import com.example.demo.fernando.domain.model.Price;
import com.example.demo.fernando.domain.repository.PriceRepository;
import com.example.demo.fernando.infrastructure.db.mapper.PriceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceCriteriaRepository criteriaRepository;

    @Override
    public List<Price> getPricesOnDemandDate(LocalDateTime demandDate, Long productId, Long brandId) {
        return criteriaRepository
                .getPricesOfDemandDate(demandDate, productId, brandId)
                .stream()
                .map(PriceEntityMapper.INSTANCE::pricesEntityToPrices)
                .collect(Collectors.toList());
    }
}