package ru.noleg.currencyrate.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.noleg.currencyrate.client.CbrCurrencyRateClient;
import ru.noleg.currencyrate.dto.ValCursDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class CbrService {
    private final CbrCurrencyRateClient cbrCurrencyRateClient;

    public CbrService(CbrCurrencyRateClient cbrCurrencyRateClient) {
        this.cbrCurrencyRateClient = cbrCurrencyRateClient;
    }

    @Cacheable("currency")
    public BigDecimal requestByCurrencyCode(String code) {
        ValCursDto response = this.cbrCurrencyRateClient.fetchCurrencyRates(LocalDate.now());

        return response.valutes().stream()
                .filter(v -> v.charCode().equals(code))
                .findFirst()
                .map(v -> new BigDecimal(v.value().replace(',', '.'))
                        .divide(new BigDecimal(v.nominal()), 4, RoundingMode.HALF_DOWN))
                .orElseThrow();
    }
}
