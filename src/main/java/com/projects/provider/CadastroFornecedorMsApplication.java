package com.projects.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class})
public class CadastroFornecedorMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroFornecedorMsApplication.class, args);
	}

}
