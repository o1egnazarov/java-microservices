package ru.noleg.processing.dto;

import java.math.BigDecimal;

public record AccountDto(Long id, Long userId, BigDecimal balance, String currencyCode) {
}
