package ru.noleg.processing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class JournalOperation {
    @EmbeddedId
    private JournalOperationId id;

    @Column(name = "c_user_id", nullable = false)
    private Long userId;

    @CreationTimestamp
    @Column(name = "c_created_at", updatable = false)
    private LocalDateTime createdAt;

    public JournalOperation() {
    }

    public JournalOperation(JournalOperationId id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public JournalOperationId getId() {
        return id;
    }

    public void setId(JournalOperationId id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
