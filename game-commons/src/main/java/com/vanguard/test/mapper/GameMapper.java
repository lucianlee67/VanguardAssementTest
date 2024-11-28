package com.vanguard.test.mapper;

import com.vanguard.test.entities.Game;
import com.vanguard.test.entities.GameDTO;
import com.vanguard.test.entities.GameSale;
import com.vanguard.test.entities.GameSaleDTO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GameMapper extends Mapper<Game> {
    List<GameDTO> selectGamesList(Game game);

    int batchInsertGames(List<Game> games);
    int aggregateGameSales();
    List<GameSaleDTO> selectGameSalesList(GameSale gameSale);
}
