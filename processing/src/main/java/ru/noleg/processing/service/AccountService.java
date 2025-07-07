package ru.noleg.processing.service;

import ru.noleg.processing.entity.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {
    Account createAccount(Account account);

    Account addMoneyToAccount(UUID transactionUid, Long accountId, BigDecimal amount);

    Account getAccount(Long accountId);

    List<Account> getAccountsByUserId(Long userId);
}
