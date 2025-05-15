package JuegosMisericordia.AppJuegosMisericordia;

import JuegosMisericordia.AppJuegosMisericordia.ui.PestanaLogin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EntityScan(basePackages = "JuegosMisericordia.model") // O el paquete real

public class AppJuegosMisericordiaApplication {

	public static void main(String[] args) {
		// Iniciar Spring Boot
		System.setProperty("java.awt.headless", "false"); // 👈 Fuerza el modo gráfico
		ConfigurableApplicationContext context = SpringApplication.run(AppJuegosMisericordiaApplication.class, args);

		// Ejecutar tu código anterior (interfaz gráfica, etc.)
		PestanaLogin pestanaLogin = new PestanaLogin();
	}
}

