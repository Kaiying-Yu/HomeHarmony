package hh.homeharmony.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for web-related settings in the HomeHarmony application.
 * This class specifically handles CORS (Cross-Origin Resource Sharing) configuration
 * to enable secure communication between the frontend and backend services.
 *
 * @Configuration indicates that this class contains Spring configuration
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    /**
     * Configures CORS settings for the application.
     * This method overrides the default CORS configuration to allow
     * specific origins, methods, and headers.
     *
     * @param registry The CorsRegistry used to register CORS configuration
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply CORS settings to all endpoints
            .allowedOrigins("http://localhost:7000")  // Allow requests from frontend development server
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow specific HTTP methods
            .allowedHeaders("*")  // Allow all headers
            .allowCredentials(true)  // Allow credentials like cookies, authorization headers
            .maxAge(3600);  // Cache CORS configuration for 1 hour (3600 seconds)
    }
}
