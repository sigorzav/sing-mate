package com.sigorzav.singmate.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(BaseEntity.AuditListener.class)
public abstract class BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    protected LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();   // 사용하지 않는 테이블은 무시
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public static class AuditListener {
        @PrePersist
        public void onPrePersist(BaseEntity entity) {
            entity.prePersist();
        }

        @PreUpdate
        public void onPreUpdate(BaseEntity entity) {
            entity.preUpdate();
        }
    }
}