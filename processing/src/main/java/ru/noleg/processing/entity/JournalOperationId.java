package ru.noleg.processing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record JournalOperationId(
        @Column(name = "c_transaction_uid", nullable = false) UUID transactionUid,
        @Column(name = "account_id", nullable = false) Long accountId
) implements Serializable {
}
