package com.challenge.infrastructure.Mock;

import org.springframework.stereotype.Component;

@Component
public class ExternalRateService {
    public Double fetchPercentage() throws Exception {
        return 12.10;
//        return null;
//        throw new Exception("Error Server");
    }
}
