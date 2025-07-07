package ru.noleg.processing.service.exchange.strategy;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.noleg.processing.entity.Account;
import ru.noleg.processing.service.AccountService;
import ru.noleg.processing.service.CurrencyServiceClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Component
public class ExchangeRubToForeignStrategy implements CurrencyExchangeStrategy {

    private final AccountService accountService;
    private final CurrencyServiceClient currencyService;

    public ExchangeRubToForeignStrategy(AccountService accountService, CurrencyServiceClient currencyService) {
        this.accountService = accountService;
        this.currencyService = currencyService;
    }

    @Override
    public boolean supports(String source, String target) {
        return "RUB".equals(source) && !"RUB".equals(target);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BigDecimal exchange(UUID uuid, Account source, Account target, BigDecimal amount) {
        BigDecimal rate = currencyService.fetchCurrencyRate(target.getCurrencyCode());
        BigDecimal multiplier = BigDecimal.ONE.divide(rate, 4, RoundingMode.HALF_DOWN);

        accountService.addMoneyToAccount(uuid, source.getId(), amount.negate());
        BigDecimal result = amount.multiply(multiplier);
        accountService.addMoneyToAccount(uuid, target.getId(), result);

        return result;
    }
}