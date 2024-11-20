package org.example;

import org.springframework.boot.SpringApplication;

public class TestReviewMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ReviewMicroserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
