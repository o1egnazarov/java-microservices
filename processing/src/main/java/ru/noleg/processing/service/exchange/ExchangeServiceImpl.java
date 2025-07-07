package ru.noleg.processing.service.exchange;

import org.springframework.stereotype.Service;
import ru.noleg.processing.entity.Account;
import ru.noleg.processing.entity.JournalOperationId;
import ru.noleg.processing.repository.JournalOperationRepository;
import ru.noleg.processing.service.AccountService;
import ru.noleg.processing.service.exchange.strategy.CurrencyExchangeStrategy;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final AccountService accountService;
    private final JournalOperationRepository journalOperationRepository;
    private final List<CurrencyExchangeStrategy> strategies;

    public ExchangeServiceImpl(AccountService accountService,
                               JournalOperationRepository journalOperationRepository,
                               List<CurrencyExchangeStrategy> strategies) {
        this.accountService = accountService;
        this.strategies = strategies;
        this.journalOperationRepository = journalOperationRepository;
    }

    // TODO логично начинать транзакцию от аккаунта источника
    public BigDecimal convert(UUID transactionUid, BigDecimal amount, Long fromId, Long toId) {
        if (journalOperationRepository.existsById(new JournalOperationId(transactionUid, fromId))) {
            throw new IllegalStateException("Transaction with uid " + transactionUid + " already exists");
        }

        Account source = accountService.getAccount(fromId);
        Account target = accountService.getAccount(toId);

        CurrencyExchangeStrategy strategy = strategies.stream()
                .filter(s -> s.supports(source.getCurrencyCode(), target.getCurrencyCode()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No strategy found for currency exchange"));

        return strategy.exchange(transactionUid, source, target, amount);
    }
}