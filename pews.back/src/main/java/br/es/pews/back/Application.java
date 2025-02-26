package br.es.pews.back;

import br.es.pews.back.security.AuthorizationServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import(AuthorizationServerConfig.class)
@EntityScan({"br.es.pews.back.models", "br.es.pews.back.form"})
@EnableJpaRepositories("br.es.pews.back.repository")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}