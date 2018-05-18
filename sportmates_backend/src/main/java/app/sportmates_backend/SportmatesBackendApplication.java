package app.sportmates_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Ez az osztály szolgál a backend elindítására.
 * @author szendrei
 * @author polozgai
 *
 */
@SpringBootApplication
public class SportmatesBackendApplication {
	/**
	 * Ezzel a metódussal indítjuk el az alkalmazást.
	 * @param args Argumentumok.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SportmatesBackendApplication.class, args);
	}
}
