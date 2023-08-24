
package com.example.demo.fernando.infrastructure.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prices")
public class PriceEntity {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "brand_id")
    private Long brandId;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "price_list")
    private Integer priceList;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "priority")
    private Integer priority;
    @Column(name = "price")
    private Double price;
    @Column(name = "curr")
    private String currency;

}
