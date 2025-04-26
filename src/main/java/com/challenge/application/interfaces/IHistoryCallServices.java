package com.challenge.application.interfaces;

import com.challenge.web.dtos.response.HistoryCallDto;
import com.challenge.domain.models.HistoryLog;

import java.util.List;

public interface IHistoryCallServices {

    List<HistoryCallDto> getHistoryCall();

    void saveHistoryLog(HistoryLog historyLog);

}
