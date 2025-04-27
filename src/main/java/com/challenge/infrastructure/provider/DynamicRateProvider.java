package com.challenge.infrastructure.provider;

import com.challenge.commons.exceptions.ProviderException;
import com.challenge.domain.repositories.DynamicRateRepository;
import com.challenge.infrastructure.Mock.ExternalRateService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DynamicRateProvider implements DynamicRateRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final ExternalRateService externalRateService;
    private static final String CACHE_KEY = "dynamicPercentage";
    private static final long TTL_MINUTES = 30;

    public DynamicRateProvider(RedisTemplate<String, String> redisTemplate, ExternalRateService externalRateService) {
        this.redisTemplate = redisTemplate;
        this.externalRateService = externalRateService;
    }

    public Double getPercentage() throws ProviderException {
        String cachedValue = redisTemplate.opsForValue().get(CACHE_KEY);
        try {
            Double percentage = externalRateService.fetchPercentage();
            if (percentage != null) {
                redisTemplate.opsForValue().set(CACHE_KEY, percentage.toString(), TTL_MINUTES, TimeUnit.MINUTES);
                return percentage;
            }
            if (cachedValue != null) {
                return Double.parseDouble(cachedValue);
            }
            throw new ProviderException("El porcentaje es nulo y no hay valor en caché.", "No se pudo obtener el porcentaje.", "ERR-001", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            if (cachedValue != null) {
                return Double.parseDouble(cachedValue);
            }
            throw new ProviderException(e.getMessage(), "No se pudo obtener el porcentaje y no hay valor en caché.", "ERR-EXT-001", HttpStatus.BAD_GATEWAY);
        }
    }
}
