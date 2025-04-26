package com.challenge.application.services;

import com.challenge.web.dtos.response.HistoryCallDto;
import com.challenge.application.interfaces.IHistoryCallServices;
import com.challenge.domain.models.HistoryLog;
import com.challenge.domain.repositories.HistoryLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HistoryCallService implements IHistoryCallServices {

    private final HistoryLogRepository historyCallRepository;

    @Override
    public List<HistoryCallDto> getHistoryCall() {

        List<HistoryLog> historyCall = historyCallRepository.findAll();

        return historyCall.stream()
                .map(historyLog -> HistoryCallDto.builder()
                        .id(historyLog.getId())
                        .timestamp(historyLog.getTimestamp().toString())
                        .endpoint(historyLog.getEndpoint())
                        .parameters(historyLog.getParameters())
                        .result(historyLog.getResult())
                        .build())
                .toList();
    }

    @Async
    @Override
    public void saveHistoryLog(HistoryLog historyLog) {
        historyCallRepository.save(historyLog);
    }
}
