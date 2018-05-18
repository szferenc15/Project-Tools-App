package app.sportmates_backend.cors_config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Ez az osztály végzi a web konfigurációs beállításokat.
 * @author szendrei
 *@author polozgai
 *
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    
	/**
	 * Web konfiguráció beállítása.
	 */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost**")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}