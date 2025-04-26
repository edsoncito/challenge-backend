package com.challenge.app.interfaces;

import com.challenge.app.dtos.response.RateDto;
import com.challenge.commons.exceptions.ProviderException;

public interface IDynamicRateServices {

    RateDto getDynamicRate(Double num1, Double num2) throws ProviderException;

}
