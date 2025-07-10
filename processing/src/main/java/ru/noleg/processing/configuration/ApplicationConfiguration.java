package ru.noleg.processing.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
    @Bean
//  TODO хз как сделать чтобы разные профили использовали эту аннотацию или не использовали
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
