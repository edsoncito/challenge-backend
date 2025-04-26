package com.challenge.domain.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryLog {

    private LocalDateTime timestamp;
    private String endpoint;
    private String parameters;
    private Double result;

}
