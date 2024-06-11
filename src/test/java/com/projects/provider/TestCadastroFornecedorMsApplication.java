package com.projects.provider;

import org.springframework.boot.SpringApplication;

public class TestCadastroFornecedorMsApplication {

	public static void main(String[] args) {
		SpringApplication.from(CadastroFornecedorMsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
