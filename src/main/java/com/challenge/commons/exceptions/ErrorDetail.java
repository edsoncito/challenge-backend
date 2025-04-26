package com.challenge.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDetail {
    private String code;
    private String description;
    private String messageTechnical;
}
