package com.vanguard.test.service;

import com.vanguard.test.entities.Game;
import com.vanguard.test.entities.GameDTO;
import com.vanguard.test.entities.GameSale;
import com.vanguard.test.entities.GameSaleDTO;

import java.math.BigDecimal;
import java.util.List;

public interface GameService {
    int add(Game game);
    int delete(Integer id);
    int update(Game game);

    Game getById(Integer id);
    List<Game> getAll();
    List<GameDTO> selectGamesList(Game game);
    void batchInsertGames(List<Game> games) throws InterruptedException;
    void aggregateGameSales() throws InterruptedException;
    BigDecimal calculateSalePrice(BigDecimal bigDecimal);
    List<GameSaleDTO> selectGameSalesList (GameSale gameSale);
}
