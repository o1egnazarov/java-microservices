package ru.noleg.processing.service.exchange.strategy;

import ru.noleg.processing.entity.Account;

import java.math.BigDecimal;
import java.util.UUID;

public interface CurrencyExchangeStrategy {
    boolean supports(String sourceCurrency, String targetCurrency);

    BigDecimal exchange(UUID uuid, Account source, Account target, BigDecimal amount);
}