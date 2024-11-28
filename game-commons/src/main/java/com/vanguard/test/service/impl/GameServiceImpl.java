package com.vanguard.test.service.impl;

import com.vanguard.test.entities.Game;
import com.vanguard.test.entities.GameDTO;
import com.vanguard.test.entities.GameSale;
import com.vanguard.test.entities.GameSaleDTO;
import com.vanguard.test.mapper.GameMapper;
import com.vanguard.test.service.GameService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class GameServiceImpl implements GameService {
    @Resource
    GameMapper gameMapper;


    @Override
    public int add(Game game) {
        return gameMapper.insertSelective(game);
    }

    @Override
    public int delete(Integer id) {
        return gameMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Game game) {
        return gameMapper.updateByPrimaryKeySelective(game);
    }

    @Override
    public Game getById(Integer id) {
        return gameMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Game> getAll() {
        return gameMapper.selectAll();
    }

    public List<GameDTO> selectGamesList(Game game){
        return gameMapper.selectGamesList(game);
    }

    public void batchInsertGames(List<Game> games) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<List<Game>> partitions = Lists.partition(games, 10000);
        for (List<Game> batch : partitions) {
            executor.submit(() -> gameMapper.batchInsertGames(batch));
        }
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.MINUTES);
    }

    @Scheduled(cron = "0 */1 * * * ?") // Every 1 minute
    public void aggregateGameSales() throws InterruptedException {
        log.info("Starting aggregate game sales");
        gameMapper.aggregateGameSales();
        log.info("Ending aggregate game sales");
    }

    public BigDecimal calculateSalePrice(BigDecimal costPrice) {
        BigDecimal taxRate = BigDecimal.valueOf(0.09);
        return costPrice.add(costPrice.multiply(taxRate));
    }

    @Override
    public List<GameSaleDTO> selectGameSalesList(GameSale gameSale) {
        return gameMapper.selectGameSalesList(gameSale);
    }


}
