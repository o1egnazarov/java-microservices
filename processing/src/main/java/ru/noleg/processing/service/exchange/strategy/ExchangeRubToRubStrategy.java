package ru.noleg.processing.service.exchange.strategy;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.noleg.processing.entity.Account;
import ru.noleg.processing.service.AccountService;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ExchangeRubToRubStrategy implements CurrencyExchangeStrategy {

    private final AccountService accountService;

    public ExchangeRubToRubStrategy(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean supports(String source, String target) {
        return "RUB".equals(source) && "RUB".equals(target);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BigDecimal exchange(UUID uuid, Account source, Account target, BigDecimal amount) {
        accountService.addMoneyToAccount(uuid, source.getId(), amount.negate());
        accountService.addMoneyToAccount(uuid, target.getId(), amount);
        return amount;
    }
}