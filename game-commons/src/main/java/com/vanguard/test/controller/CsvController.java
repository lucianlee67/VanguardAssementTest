package com.vanguard.test.controller;

import com.vanguard.test.util.CsvGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

@RestController
public class CsvController {

    @GetMapping("/generateCsv")
    public String generateCsv(@RequestParam(defaultValue = "1000000") int totalRecords) {
        try {
            String fileName = Paths.get(System.getProperty("user.dir"), "game_sales.csv").toString();
            return CsvGenerator.generateCsv(fileName, totalRecords);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
