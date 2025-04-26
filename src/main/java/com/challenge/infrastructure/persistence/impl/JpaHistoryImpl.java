package com.challenge.infrastructure.persistence.impl;

import com.challenge.domain.models.HistoryLog;
import com.challenge.domain.repositories.HistoryLogRepository;
import com.challenge.infrastructure.persistence.entity.HistoryLogEntity;
import com.challenge.infrastructure.persistence.repository.SpringDataHistoryLogRepository;

import java.util.List;

public class JpaHistoryImpl implements HistoryLogRepository {

    private final SpringDataHistoryLogRepository springDataHistoryLogRepository;

    public JpaHistoryImpl(SpringDataHistoryLogRepository springDataHistoryLogRepository) {
        this.springDataHistoryLogRepository = springDataHistoryLogRepository;
    }

    @Override
    public void save(HistoryLog historyLog) {
        HistoryLogEntity entity = new HistoryLogEntity();
        entity.setTimestamp(historyLog.getTimestamp());
        entity.setEndpoint(historyLog.getEndpoint());
        entity.setParameters(historyLog.getParameters());
        entity.setResult(historyLog.getResult());
        springDataHistoryLogRepository.save(entity);
    }

    @Override
    public List<HistoryLog> findAll() {
        List<HistoryLogEntity> entities = springDataHistoryLogRepository.findAll();
        return entities.stream()
                .map(entity -> HistoryLog.builder()
                        .id(entity.getId())
                        .timestamp(entity.getTimestamp())
                        .endpoint(entity.getEndpoint())
                        .parameters(entity.getParameters())
                        .result(entity.getResult())
                        .build())
                .toList();
    }
}
