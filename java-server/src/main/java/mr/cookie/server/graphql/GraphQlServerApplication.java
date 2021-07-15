package mr.cookie.server.graphql;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@Getter
@SpringBootApplication
public class GraphQlServerApplication implements CommandLineRunner {

	@Value("${spring.application.name:@null}")
	private @Nullable String appName;

	public static void main(@Nullable String[] args) {
		SpringApplication.run(GraphQlServerApplication.class, args);
	}

	@Override
	public void run(@Nullable String... args) {
		log.info(">>> The {} application started successfully!", appName);
	}

}
