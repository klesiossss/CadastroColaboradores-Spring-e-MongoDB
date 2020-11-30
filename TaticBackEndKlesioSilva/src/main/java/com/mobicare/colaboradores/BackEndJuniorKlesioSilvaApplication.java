package com.mobicare.colaboradores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mobicare.colaboradores.repository","com.mobicare.colaboradores.service","com.mobicare.colaboradores.repository","com.mobicare.colaboradores.controller"})
public class BackEndJuniorKlesioSilvaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndJuniorKlesioSilvaApplication.class, args);
	}

}
