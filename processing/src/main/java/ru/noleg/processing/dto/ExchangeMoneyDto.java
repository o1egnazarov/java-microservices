package ru.noleg.processing.dto;

import java.math.BigDecimal;

public record ExchangeMoneyDto(Long fromAccountId, Long toAccountId, BigDecimal amount) {
}
