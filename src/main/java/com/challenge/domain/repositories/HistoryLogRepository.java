package com.challenge.domain.repositories;

import com.challenge.commons.exceptions.ProviderException;
import com.challenge.domain.models.HistoryLog;

import java.util.List;

public interface HistoryLogRepository {
    void save(HistoryLog historyLog) throws ProviderException;
    List<HistoryLog> findAll() throws ProviderException;
}
