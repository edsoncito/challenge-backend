package com.challenge.infrastructure.provider;

import com.challenge.commons.exceptions.ProviderException;
import com.challenge.infrastructure.Mock.ExternalRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class DynamicRateProviderTest {

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @Mock
    private ExternalRateService externalRateService;

    @InjectMocks
    private DynamicRateProvider dynamicRateProvider;

    private static final String CACHE_KEY = "dynamicPercentage";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void testGetPercentage_WithValidPercentage() throws Exception {
        Double expectedPercentage = 12.10;
        String cachedValue = null;

        RedisTemplate<String, String> redisTemplate = mock(RedisTemplate.class);
        ValueOperations<String, String> valueOperations = mock(ValueOperations.class);

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        when(valueOperations.get(CACHE_KEY)).thenReturn(cachedValue);

        ExternalRateService externalRateService = mock(ExternalRateService.class);
        doReturn(expectedPercentage).when(externalRateService).fetchPercentage();

        DynamicRateProvider dynamicRateProvider = new DynamicRateProvider(redisTemplate, externalRateService);

        Double result = dynamicRateProvider.getPercentage();

        assertEquals(expectedPercentage, result);
    }

    @Test
    void testGetPercentage_WithExceptionThrownFromExternalService() throws Exception {
        String cachedValue = null;

        when(redisTemplate.opsForValue().get(CACHE_KEY)).thenReturn(cachedValue);

        when(externalRateService.fetchPercentage()).thenThrow(new RuntimeException("Error Server"));

        ProviderException thrown = assertThrows(ProviderException.class, () -> {
            dynamicRateProvider.getPercentage();
        });

        assertEquals("Error Server", thrown.getMessage());
        assertEquals(HttpStatus.BAD_GATEWAY, thrown.getStatus());
    }

    @Test
    void testGetPercentage_WithExceptionAndCachedValue() throws Exception {
        Double cachedPercentage = 10.0;
        String cachedValue = "10.0";
        when(redisTemplate.opsForValue().get(CACHE_KEY)).thenReturn(cachedValue);

        when(externalRateService.fetchPercentage()).thenReturn(null);

        doReturn(null).when(externalRateService).fetchPercentage();

        Double result = dynamicRateProvider.getPercentage();

        assertEquals(cachedPercentage, result); // El valor debe ser tomado del caché
    }


}