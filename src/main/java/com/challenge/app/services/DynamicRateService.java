package com.challenge.app.services;

import com.challenge.app.dtos.response.RateDto;
import com.challenge.app.interfaces.IDynamicRateServices;
import com.challenge.commons.exceptions.ProviderException;
import com.challenge.domain.models.HistoryLog;
import com.challenge.domain.repositories.DynamicRateRepository;
import com.challenge.domain.repositories.HistoryLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DynamicRateService implements IDynamicRateServices {

    private final DynamicRateRepository dynamicRateRepository;
    private final HistoryLogRepository historyLogRepository;

    @Override
    public RateDto getDynamicRate(Double num1, Double num2) throws ProviderException {
        double sum = num1 + num2;
        Double percentage = dynamicRateRepository.getPercentage();
        double rate = sum + ( sum * (percentage / 100));

//        HistoryLog historyLog = new HistoryLog();
//        historyLog.setParameters(num1 + " + " + num2);
//        historyLog.setResult(sum);
//        historyLog.setEndpoint("DynamicRateService");
//        historyLogRepository.save(historyLog);

        System.out.println("Sum: " + rate);
        return RateDto.builder()
                .codeResponse("00")
                .messageResponse("Success")
                .build();
    }
}
