package com.challenge.web.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryCallDto {

    private Long id;
    private String timestamp;
    private String endpoint;
    private String parameters;
    private Double result;

}
