package ru.noleg.processing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.noleg.processing.entity.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUserId(Long userId);
}
