package ru.noleg.processing.service.exchange;

import java.math.BigDecimal;
import java.util.UUID;

public interface ExchangeService {
    BigDecimal convert(UUID transactionUid, BigDecimal amount, Long fromId, Long toId);
}
