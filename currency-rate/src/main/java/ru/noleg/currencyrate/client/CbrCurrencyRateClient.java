package ru.noleg.currencyrate.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.noleg.currencyrate.dto.ValCursDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CbrCurrencyRateClient {

    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    @Value("${current.rate.url}")
    private String BASE_URL;

    private final RestTemplate restTemplate;

    public CbrCurrencyRateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // TODO: 30/03/2023
    public ValCursDto fetchCurrencyRates(LocalDate date) {
        return this.restTemplate.getForObject(this.buildURIRequest(BASE_URL, date), ValCursDto.class);
    }

    public String buildURIRequest(String baseUrl, LocalDate date) {
        return UriComponentsBuilder.
                fromUriString(baseUrl)
                .queryParam("date_req", DATE_TIME_FORMATTER.format(date))
                .toUriString();
    }
}