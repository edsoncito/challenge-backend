package com.challenge.infrastructure.persistence.impl;

import com.challenge.commons.exceptions.ProviderException;
import com.challenge.domain.models.HistoryLog;
import com.challenge.domain.repositories.HistoryLogRepository;
import com.challenge.infrastructure.persistence.entities.HistoryLogEntity;
import com.challenge.infrastructure.persistence.repository.SpringDataHistoryLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaHistoryImpl implements HistoryLogRepository {

    private final SpringDataHistoryLogRepository springDataHistoryLogRepository;

    public JpaHistoryImpl(SpringDataHistoryLogRepository springDataHistoryLogRepository) {
        this.springDataHistoryLogRepository = springDataHistoryLogRepository;
    }

    @Override
    public void save(HistoryLog historyLog) {
        HistoryLogEntity entity = new HistoryLogEntity();
        entity.setEndpoint(historyLog.getEndpoint());
        entity.setParameters(historyLog.getParameters());
        entity.setResult(historyLog.getResult());
        springDataHistoryLogRepository.save(entity);
    }

    @Override
    public List<HistoryLog> findAll() {
        try {
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
        } catch (Exception e) {
            throw new ProviderException("Ocurrio un error con la conexion Db","ERR-002", e.getMessage());
        }
    }
}
