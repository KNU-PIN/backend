package com.example.knupin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PostConstruct;
import java.util.TimeZone;



@SpringBootApplication
public class KnuPinApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnuPinApplication.class, args);
	}

	@PostConstruct
    public void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
