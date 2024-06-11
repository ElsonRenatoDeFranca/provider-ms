package com.projects.provider;

import org.springframework.boot.SpringApplication;

public class TestProviderMsApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProviderMsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
