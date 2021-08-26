package mx.uady.musicon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MusiconApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusiconApplication.class, args);
	}


}
