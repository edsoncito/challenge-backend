package com.challenge.domain.repositories;

import com.challenge.domain.models.HistoryLog;

import java.util.List;

public interface HistoryLogRepository {
    void save(HistoryLog historyLog);
    List<HistoryLog> findAll();
}
