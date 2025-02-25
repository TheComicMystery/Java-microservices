package org.example;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product_service", r -> r.path("/api/products/**")
                        .uri("http://localhost:8080"))
                .route("reviews_service", r -> r.path("/api/reviews/**")
                        .uri("http://localhost:8081"))
                .build();
    }
}
