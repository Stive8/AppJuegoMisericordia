package JuegosMisericordia;

import JuegosMisericordia.ui.PestanaLogin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "JuegosMisericordia.model")
@EnableJpaRepositories(basePackages = "JuegosMisericordia.repository")

public class AppJuegosMisericordiaApplication {

	public static void main(String[] args) {
		// Iniciar Spring Boot
		System.setProperty("java.awt.headless", "false"); // 👈 Fuerza el modo gráfico
		ConfigurableApplicationContext context = SpringApplication.run(AppJuegosMisericordiaApplication.class, args);

		// Ejecutar tu código anterior (interfaz gráfica, etc.)
		PestanaLogin pestanaLogin = new PestanaLogin();
	}
}

