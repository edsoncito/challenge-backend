package com.challenge.application.interfaces;

import com.challenge.web.dtos.response.RateDto;
import com.challenge.commons.exceptions.ProviderException;

public interface IDynamicRateServices {

    RateDto getDynamicRate(Double num1, Double num2) throws ProviderException;

}
