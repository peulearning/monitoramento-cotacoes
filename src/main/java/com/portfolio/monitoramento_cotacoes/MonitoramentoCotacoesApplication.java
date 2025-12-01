package com.portfolio.monitoramento_cotacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MonitoramentoCotacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoramentoCotacoesApplication.class, args);
	}

}
