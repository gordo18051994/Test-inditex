package com.example.demo.fernando.domain.repository;

import com.example.demo.fernando.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

    List<Price> getPricesOnDemandDate(LocalDateTime demandDate, Long productId, Long brandId);
}
