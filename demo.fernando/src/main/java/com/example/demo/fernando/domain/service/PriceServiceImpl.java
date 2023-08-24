package com.example.demo.fernando.domain.service;

import com.example.demo.fernando.domain.model.Price;
import com.example.demo.fernando.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService{

    private final PriceRepository priceRepository;

    /**
     * @param demandDate
     * @param productId
     * @param brandId
     * @return
     */
    @Override
    public Price getPricesOnDemandDate(LocalDateTime demandDate, Long productId, Long brandId) {
        return priceRepository.getPricesOnDemandDate(demandDate, productId, brandId)
                .stream().max(Comparator.comparingInt(Price::getPriority)).orElse(null);
    }
}
