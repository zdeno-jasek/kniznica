package sk.llarik.kniznica.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"sk.llarik.kniznica.domain", "sk.llarik.kniznica.infrastructure.repository"})
public class KniznicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KniznicaApplication.class, args);
	}

}
