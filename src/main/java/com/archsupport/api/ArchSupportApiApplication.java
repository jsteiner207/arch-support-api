package com.archsupport.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ArchSupportApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchSupportApiApplication.class, args);
	}

}
