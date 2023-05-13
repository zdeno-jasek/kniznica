package sk.javakurz.kniznica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("sk.javakurz.kniznica.domain") 
@EntityScan("sk.javakurz.kniznica.domain")
@SpringBootApplication
public class KniznicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KniznicaApplication.class, args);
	}

}
