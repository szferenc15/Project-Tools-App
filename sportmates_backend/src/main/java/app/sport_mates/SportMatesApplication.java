package app.sportmates_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SportMatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportMatesApplication.class, args);
	}
}
