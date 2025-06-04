package com.ism.absences;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class AbsencesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbsencesBackendApplication.class, args);
	}
	

}
