package com.challenge.infrastructure.persistence.repository;

import com.challenge.infrastructure.persistence.entity.HistoryLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataHistoryLogRepository extends JpaRepository<HistoryLogEntity, Long> {
}

