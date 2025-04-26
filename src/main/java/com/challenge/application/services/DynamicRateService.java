package com.challenge.application.services;

import com.challenge.web.dtos.response.RateDto;
import com.challenge.application.interfaces.IDynamicRateServices;
import com.challenge.application.interfaces.IHistoryCallServices;
import com.challenge.commons.exceptions.ProviderException;
import com.challenge.domain.models.HistoryLog;
import com.challenge.domain.repositories.DynamicRateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DynamicRateService implements IDynamicRateServices {

    private final DynamicRateRepository dynamicRateRepository;
    private final IHistoryCallServices historyCall;

    @Override
    public RateDto getDynamicRate(Double num1, Double num2) throws ProviderException {
        double sum = num1 + num2;
        Double percentage = dynamicRateRepository.getPercentage();
        double rate = sum + ( sum * (percentage / 100));

        HistoryLog historyLog = new HistoryLog();
        historyLog.setParameters(num1 + " + " + num2);
        historyLog.setResult(sum);
        historyLog.setEndpoint("DynamicRateService");
        historyCall.saveHistoryLog(historyLog);

        System.out.println("Sum: " + rate);
        return RateDto.builder()
                .codeResponse("00")
                .messageResponse("Success")
                .build();
    }
}
