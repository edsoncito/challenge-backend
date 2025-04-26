package com.challenge.infrastructure.Mock;

import org.springframework.stereotype.Component;

@Component
public class ExternalRateService {
    public Double fetchPercentage() throws Exception {

        /* Descomentar para que la llamda sea exitosa*/
        return 12.10;

        /* Descomentar para que llamada al servicio reciba null como respuesta */
//        return null;

        /* Descomentar para que la llamada sea una exception simulando que el servicio esta abajo o fallo*/
//        throw new Exception("Error Server");
    }
}
