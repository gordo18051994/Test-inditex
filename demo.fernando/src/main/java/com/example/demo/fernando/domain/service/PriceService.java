package com.example.demo.fernando.domain.service;

import com.example.demo.fernando.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceService {

    Price getPricesOnDemandDate(LocalDateTime demandDate, Long productId, Long brandId);

}
