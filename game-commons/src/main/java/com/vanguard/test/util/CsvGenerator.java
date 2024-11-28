package com.vanguard.test.util;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CsvGenerator {
    public static String generateCsv(String fileName, int totalRecords) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName),
                CSVWriter.DEFAULT_SEPARATOR,   // Separator (default: ',')
                CSVWriter.NO_QUOTE_CHARACTER,  // Disable quotes
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {
            String[] header = {"id", "game_no", "game_name", "game_code", "type",
                    "cost_price", "tax", "sale_price", "date_of_sale"};
            writer.writeNext(header);

            Random random = new Random();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            for (int i = 1; i <= totalRecords; i++) {
                int gameNo = random.nextInt(100) + 1;
                String gameName = "Game" + gameNo;
                String gameCode = String.format("G%04d", gameNo);
                int type = random.nextInt(2) + 1;
                double costPrice = round(random.nextDouble() * 100, 2);
                double tax = round(costPrice * 0.09, 2);
                double salePrice = round(costPrice + tax, 2);
                String dateOfSale = generateRandomDate("2024-04-01", "2024-04-30", random).format(formatter);

                String[] record = {
                        String.valueOf(i),
                        String.valueOf(gameNo),
                        gameName,
                        gameCode,
                        String.valueOf(type),
                        String.valueOf(costPrice),
                        String.valueOf(tax),
                        String.valueOf(salePrice),
                        dateOfSale
                };

                writer.writeNext(record);
            }
            return "CSV generated successfully at: " + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "CSV generation failed: " + e.getMessage();
        }
    }

    private static double round(double value, int places) {
        return BigDecimal.valueOf(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }

    private static LocalDateTime generateRandomDate(String startDate, String endDate, Random random) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        long daysBetween = end.toEpochDay() - start.toEpochDay();
        long randomDay = start.toEpochDay() + random.nextLong(daysBetween + 1);
        return LocalDate.ofEpochDay(randomDay).atTime(random.nextInt(24), random.nextInt(60), random.nextInt(60));
    }
}
