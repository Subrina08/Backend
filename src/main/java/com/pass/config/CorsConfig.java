package com.pass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // Set the allowed origins (add multiple origins as needed)
        config.addAllowedOrigin("http://localhost:3000"); // Replace with the frontend URL you want to allow
        // Set the allowed HTTP methods (you can restrict specific methods as needed)
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        // Set the allowed headers (you can restrict specific headers as needed)
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("Authorization"); // Add more headers if required
        config.setAllowCredentials(true); // You may need this for authentication purposes
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
