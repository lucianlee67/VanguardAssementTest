package com.vanguard.test.controller;


import com.vanguard.test.entities.*;
import com.vanguard.test.service.GameService;
import com.vanguard.test.service.ImportProgressService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import com.vanguard.test.resp.ResultData;
import com.vanguard.test.resp.ReturnCodeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class GameController {
    @Resource
    GameService gameService;

    @Resource
    ImportProgressService importProgressService;


    @PostMapping(value = "/game/add")
    @Operation(summary = "ADD",description = "Add Games Sale")
    public ResultData<String> addGame(@RequestBody Game game)
    {
        try
        {
            game.setTax(gameService.calculateSalePrice(game.getCostPrice()));
            int i = gameService.add(game);
            return ResultData.success("Successfully Insert，Return："+i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }

    }

    @DeleteMapping(value = "/game/del/{id}")
    @Operation(summary = "Delete",description = "Delete Game Sales")
    public ResultData<Integer> deleteGame(@PathVariable("id") Integer id) {
        if(id < 0) throw new RuntimeException("ID can't be -ve");
        int i = gameService.delete(id);
        return ResultData.success(i);
    }

    @PutMapping(value = "/game/update")
    @Operation(summary = "Edit",description = "Edit Game Sales")
    public ResultData<String> updateGame(@RequestBody Game game)
    {
//        if(id < 0) throw new RuntimeException("ID can't be -ve");
        game.setTax(gameService.calculateSalePrice(game.getCostPrice()));
        int i = gameService.update(game);
        return ResultData.success("Successfully Update，Return："+i);
    }



    @GetMapping(value = "/game/get/{id}")
    @Operation(summary = "Find By Game Id",description = "Find Game Sales")
    public ResultData<GameDTO> getById(@PathVariable("id") Integer id)
    {
        if(id < 0) throw new RuntimeException("ID can't be -ve");

        Game game = gameService.getById(id);
        GameDTO dto = new GameDTO();
        BeanUtils.copyProperties(game, dto);
        return ResultData.success(dto);
    }

    @PostMapping("/import")
    @Operation(summary = "Import CSV",description = "Import CSV")
    public ResultData<String> importCsvFile(@RequestParam("file") MultipartFile file) {
        ImportProgress importProgress = new ImportProgress();
        importProgress.setFileName(file.getOriginalFilename());

        if (file.isEmpty()) {
            importProgress.setStatus("FAILED");
            importProgressService.add(importProgress);
            return ResultData.fail("", "File is empty");
        }
        importProgress.setStatus("IN_PROGRESS");
        int count = 0;
        int import_id = importProgressService.add(importProgress);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            List<Game> games = new ArrayList<>();
            reader.readLine(); // Skip header line

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                Game game = new Game();
                game.setGameNo(Integer.parseInt(fields[1]));
                game.setGameName(fields[2]);
                game.setGameCode(fields[3]);
                game.setType(Integer.parseInt(fields[4]));
                game.setCostPrice(new BigDecimal(fields[5]));
                game.setTax(new BigDecimal(fields[6]));
                game.setSalePrice(new BigDecimal(fields[7]));
                String dateStr = fields[8];
                game.setDateOfSale(LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

                games.add(game);
                count++;
            }
            gameService.batchInsertGames(games);
            importProgress.setStatus("COMPLETED");
            importProgress.setProcessedRecords(count);
            importProgress.setCompletedAt(LocalDateTime.now());
            importProgressService.update(importProgress);
            return ResultData.success("File imported successfully");
        } catch (Exception e) {
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }

    }

    @GetMapping("/getGameSales")
    @Operation(summary = "Get Game Sales",description = "Return Game Sales")
    public ResultData<List<GameDTO>> list(@RequestBody Game game)
    {
        startPage(game);
        List<GameDTO> list = gameService.selectGamesList(game);

        return ResultData.success(list);
    }

    @GetMapping("/getTotalSales")
    @Operation(summary = "Get Total Sales",description = "Return Total Sales")
    public ResultData<List<GameSaleDTO>> selectGameSalesList(@RequestBody GameSale gameSale){
        if(gameSale != null) {
            if(gameSale.getGameNo() != null && gameSale.getGameNo() < 0) throw new RuntimeException("ID can't be -ve");
            if(gameSale.getGameNo() != null && gameSale.getGameNo() > 100) throw new RuntimeException("ID can't more than 100");
        }
        List<GameSaleDTO> list = gameService.selectGameSalesList(gameSale);
        return ResultData.success(list);
    }

    public void startPage(Game game){
        Map<String, Object> params = new HashMap<>();
        if(game.getParams().containsKey("pageNum")){
            if((int)game.getParams().get("pageNum") > 0){
                game.getParams().put("offSet", ((int)game.getParams().get("pageNum") - 1 ) * 100);
            }else{
                game.getParams().put("offSet", 0);
            }
        }else{
            game.getParams().put("offSet", ( 1 - 1 ) * 100);
        }
    }

}
