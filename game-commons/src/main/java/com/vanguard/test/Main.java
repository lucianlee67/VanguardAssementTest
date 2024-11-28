package com.vanguard.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.vanguard.test.mapper") //import tk.mybatis.spring.annotation.MapperScan;
@EnableScheduling
public class Main {
    public static void main(String[] args)
    {
        SpringApplication.run(Main.class,args);
    }
}
