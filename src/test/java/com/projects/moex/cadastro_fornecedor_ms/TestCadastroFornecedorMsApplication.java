package com.projects.moex.cadastro_fornecedor_ms;

import org.springframework.boot.SpringApplication;

public class TestCadastroFornecedorMsApplication {

	public static void main(String[] args) {
		SpringApplication.from(CadastroFornecedorMsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
