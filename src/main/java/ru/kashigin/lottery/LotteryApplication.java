package ru.kashigin.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.kashigin.lottery")
public class LotteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryApplication.class, args);
	}

}
