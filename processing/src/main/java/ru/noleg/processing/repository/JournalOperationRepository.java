package ru.noleg.processing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.noleg.processing.entity.JournalOperation;
import ru.noleg.processing.entity.JournalOperationId;

public interface JournalOperationRepository extends JpaRepository<JournalOperation, JournalOperationId> {
}
