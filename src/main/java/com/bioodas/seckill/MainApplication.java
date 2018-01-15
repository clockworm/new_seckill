package com.bioodas.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MainApplication{
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MainApplication.class, args);
	}
}
