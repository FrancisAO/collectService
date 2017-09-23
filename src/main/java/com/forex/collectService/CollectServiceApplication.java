package com.forex.collectService;

import com.forex.collectService.controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@PropertySource("classpath:application-default.properties")
@PropertySource("classpath:application-${spring.profiles.active}.properties")
@PropertySource("file:${user.home}/.collectService/application-${spring.profiles.active}.properties")
public class CollectServiceApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(CollectServiceApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		jdbcTemplate.execute("INSERT INTO currencypairaskbid(currencyPair, buy, sell, time) VALUES ('EURUSD', 2.0, 2.0, now());");

	}
}
