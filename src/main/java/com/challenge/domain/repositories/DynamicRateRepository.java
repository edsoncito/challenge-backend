package com.challenge.domain.repositories;

import com.challenge.commons.exceptions.ProviderException;

public interface DynamicRateRepository {
    Double getPercentage() throws ProviderException;
}