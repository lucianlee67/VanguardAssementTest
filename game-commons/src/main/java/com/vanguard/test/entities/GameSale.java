package com.vanguard.test.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GameSale extends BaseEntity{
    private Integer gameNo;
    private BigDecimal totalGamesSold;
    private BigDecimal totalSales;
    private LocalDateTime date;
}
