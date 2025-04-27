package com.challenge.application.services;

import com.challenge.application.interfaces.IHistoryCallServices;
import com.challenge.commons.exceptions.ProviderException;
import com.challenge.domain.repositories.DynamicRateRepository;
import com.challenge.infrastructure.Mock.ExternalRateService;
import com.challenge.web.dtos.response.RateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DynamicRateServiceTest {

    @Mock
    private DynamicRateRepository dynamicRateRepository;

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private ExternalRateService externalRateService;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @Mock
    private IHistoryCallServices historyCall;

    @InjectMocks
    private DynamicRateService dynamicRateService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void testGetDynamicRate_Success() throws ProviderException {
        Double num1 = 100.0;
        Double num2 = 50.0;
        String requestUrl = "http://localhost/dynamic-rate";
        Double percentage = 10.0;

        when(dynamicRateRepository.getPercentage()).thenReturn(percentage);

        RateDto result = dynamicRateService.getDynamicRate(num1, num2, requestUrl);

        assertNotNull(result);
        assertEquals("00", result.getCodeResponse());
        assertEquals("Success", result.getMessageResponse());
    }

    @Test
    void testGetDynamicRate_Exception() throws Exception {
        Double num1 = 10.0;
        Double num2 = 20.0;
        String requestUrl = "some/request/url";

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get(anyString())).thenReturn(null);  // Redis no tiene valor

        doReturn(null).when(externalRateService).fetchPercentage();

        when(dynamicRateRepository.getPercentage())
                .thenThrow(new ProviderException("El porcentaje es nulo y no hay valor en caché.", "No se pudo obtener el porcentaje.", "ERR-001", HttpStatus.BAD_REQUEST));

        ProviderException thrown = assertThrows(ProviderException.class, () -> {
            dynamicRateService.getDynamicRate(num1, num2, requestUrl);
        });

        assertEquals("Error Server", thrown.getMessage());

    }

}