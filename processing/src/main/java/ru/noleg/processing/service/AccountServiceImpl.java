package ru.noleg.processing.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.noleg.processing.entity.Account;
import ru.noleg.processing.entity.JournalOperation;
import ru.noleg.processing.entity.JournalOperationId;
import ru.noleg.processing.repository.AccountRepository;
import ru.noleg.processing.repository.JournalOperationRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final JournalOperationRepository journalOperationRepository;

    public AccountServiceImpl(AccountRepository accountRepository,
                              JournalOperationRepository journalOperationRepository) {
        this.accountRepository = accountRepository;
        this.journalOperationRepository = journalOperationRepository;
    }

    @Override
    @Transactional
    public Account createAccount(Account account) {
        account.setBalance(BigDecimal.ZERO);
        return this.accountRepository.save(account);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Account addMoneyToAccount(UUID transactionUid, Long accountId, BigDecimal amount) {

        if (journalOperationRepository.existsById(new JournalOperationId(transactionUid, accountId))) {
            throw new IllegalArgumentException("Transaction with id " + transactionUid + " already exists.");
        }

        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new IllegalArgumentException("Account with id " + accountId + " not found.")
        );

        BigDecimal balance = account.getBalance();
        account.setBalance(balance.add(amount));

        Account updatedAccount = this.accountRepository.save(account);
        this.journalOperationRepository.save(
                new JournalOperation(new JournalOperationId(transactionUid, accountId), account.getUserId())
        );

        return updatedAccount;
    }

    @Override
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(
                () -> new IllegalArgumentException("Account with id " + accountId + " not found.")
        );
    }

    @Override
    public List<Account> getAccountsByUserId(Long userId) {
        return accountRepository.findAllByUserId(userId);
    }


}
