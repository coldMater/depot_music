package co.coldflow.depot_music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DepotMusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepotMusicApplication.class, args);
	}

}
