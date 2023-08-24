package com.example.demo.fernando.application.controller;

import com.example.demo.fernando.application.controller.dto.PriceDTO;
import com.example.demo.fernando.application.controller.dto.PriceFilterDTO;
import com.example.demo.fernando.application.controller.mapper.PriceDtoMapper;
import com.example.demo.fernando.domain.model.Price;
import com.example.demo.fernando.domain.service.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/fernando")
public class PriceController {

    private final PriceService priceService;

    @GetMapping(value = "/priceOnDemandDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDTO> getPricesOnDemandDate(PriceFilterDTO filter) {
        Price price = priceService.getPricesOnDemandDate(filter.getDemandDate(), filter.getProductId(), filter.getBrandId());
        PriceDTO response = PriceDtoMapper.INSTANCE.toPriceDTO(price);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
