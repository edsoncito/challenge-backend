package com.challenge.commons.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProviderException extends RuntimeException {

    private HttpStatus status;
    private String code;
    private String description;

    public ProviderException(String messageTechnical, String description, String code, HttpStatus status) {
        super(messageTechnical);
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public ProviderException(String description, String code, String messageTechnical) {
        super(messageTechnical);
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.description = description;
        this.code = code;
    }

    public ProviderException(String description, String code, HttpStatus status) {
        this.description = description;
        this.code = code;
        this.status = status;
    }
}
