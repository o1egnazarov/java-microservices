package ru.noleg.processing.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Component
public class CurrencyServiceClient {

    private final RestTemplate restTemplate;

    @Value("${currency.service.url}")
    private String currencyServiceUrl;

    public CurrencyServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BigDecimal fetchCurrencyRate(String code) {
        String url = UriComponentsBuilder
                .fromUriString(currencyServiceUrl + "/currency")
                .queryParam("code", code)
                .toUriString();

        return restTemplate.getForObject(url, BigDecimal.class);
    }
}