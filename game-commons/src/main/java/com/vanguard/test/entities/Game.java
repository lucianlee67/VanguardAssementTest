package com.vanguard.test.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name = "game_sales")
@ToString
@Schema(title = " Game Entity")
public class Game extends BaseEntity {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "game_no", nullable = false)
    private Integer gameNo;

    @Column(name = "game_name", length = 20, nullable = false)
    private String gameName;

    @Column(name = "game_code", length = 5, nullable = false)
    private String gameCode;

    @Column(name = "type", nullable = false)
    private Integer type; // 1 = Online, 2 = Offline

    @Column(name = "cost_price", nullable = false)
    private BigDecimal costPrice;

    @Column(name = "tax", nullable = false)
    private BigDecimal tax;

    @Column(name = "sale_price", nullable = false)
    private BigDecimal salePrice;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date_of_sale", nullable = false)
    private LocalDateTime dateOfSale;

}
