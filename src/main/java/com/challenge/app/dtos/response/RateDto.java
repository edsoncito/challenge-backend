package com.challenge.app.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {
    private String codeResponse;
    private String messageResponse;
}
