package com.vanguard.test.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO implements Serializable
{
    private Long id;

    private Integer gameNo;

    private String gameName;

    private String gameCode;

    private Integer type; // 1 = Online, 2 = Offline

    private BigDecimal costPrice;

    private BigDecimal tax;

    private BigDecimal salePrice;

    private LocalDateTime dateOfSale;
}
