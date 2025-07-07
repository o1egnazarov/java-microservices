package ru.noleg.processing.conroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.noleg.processing.dto.CreateAccountDto;
import ru.noleg.processing.dto.ExchangeMoneyDto;
import ru.noleg.processing.dto.PutAccountMoneyDto;
import ru.noleg.processing.entity.Account;
import ru.noleg.processing.mapper.AccountMapper;
import ru.noleg.processing.service.AccountService;
import ru.noleg.processing.service.exchange.ExchangeService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/processing")
public class ProcessingController {
    private final AccountService accountService;
    private final ExchangeService exchangeService;
    private final AccountMapper accountMapper;

    public ProcessingController(AccountService accountService,
                                ExchangeService exchangeService,
                                AccountMapper accountMapper) {
        this.accountService = accountService;
        this.exchangeService = exchangeService;
        this.accountMapper = accountMapper;
    }

    @PostMapping("/accounts")
    public ResponseEntity<CreateAccountDto> createAccount(@RequestBody CreateAccountDto createAccountDto) {
        Account account = accountMapper.mapToEntity(createAccountDto);
        Account createdAccount = accountService.createAccount(account);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountMapper.mapToDto(createdAccount));
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<CreateAccountDto> getAccount(@PathVariable Long accountId) {
        Account account = accountService.getAccount(accountId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountMapper.mapToDto(account));
    }

    @GetMapping("/accounts/users/{userId}")
    public ResponseEntity<List<CreateAccountDto>> getAccountsByUserId(@PathVariable Long userId) {
        List<Account> accounts = accountService.getAccountsByUserId(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountMapper.mapToDtoList(accounts));
    }

    @PutMapping("/accounts/{accountId}/money")
    public ResponseEntity<CreateAccountDto> addMoneyToAccount(
            @PathVariable Long accountId,
            @RequestBody PutAccountMoneyDto putAccountMoneyDto
    ) {
        Account account = accountService.addMoneyToAccount(
                putAccountMoneyDto.transactionUid(),
                accountId,
                putAccountMoneyDto.amount()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountMapper.mapToDto(account));
    }

    @PostMapping("/exchanges/{uuid}")
    public ResponseEntity<BigDecimal> exchangeMoney(
            @PathVariable UUID uuid,
            @RequestBody ExchangeMoneyDto exchangeMoneyDto
    ) {
        BigDecimal result = exchangeService.convert(
                uuid,
                exchangeMoneyDto.amount(),
                exchangeMoneyDto.fromAccountId(),
                exchangeMoneyDto.toAccountId()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
