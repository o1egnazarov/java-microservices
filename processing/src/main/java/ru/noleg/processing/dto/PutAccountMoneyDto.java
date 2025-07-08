package ru.noleg.processing.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PutAccountMoneyDto(UUID transactionUid, BigDecimal amount) {
}
