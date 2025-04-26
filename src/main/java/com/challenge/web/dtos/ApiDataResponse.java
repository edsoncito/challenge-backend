package com.challenge.web.dtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDataResponse<T> {
    private T data;

    public static <T> ApiDataResponse<T> of(T data) {
        return ApiDataResponse.<T>builder().data(data).build();
    }
}
