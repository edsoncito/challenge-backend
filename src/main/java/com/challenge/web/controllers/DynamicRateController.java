package com.challenge.web.controllers;

import com.challenge.web.dtos.ApiDataResponse;
import com.challenge.web.dtos.response.RateDto;
import com.challenge.application.interfaces.IDynamicRateServices;
import com.challenge.commons.exceptions.ErrorDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api")
@Tag(name = "Dynamic rate", description = "Calcutation of dynamic rate")
public class DynamicRateController {

    private final IDynamicRateServices iDynamicRateServices;

    public DynamicRateController(IDynamicRateServices iDynamicRateServices) {
        this.iDynamicRateServices = iDynamicRateServices;
    }

    @Operation(description = "Calculation dynamic of rate", operationId = "get List Devices ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DynamicRateController.ObjectResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "401", description = "Access Denied.", content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal error.", content = @Content(schema = @Schema(implementation = ErrorDetail.class)))})
    @PostMapping(path = "/dynamic-rate")
    public ResponseEntity<ApiDataResponse<RateDto>> dynamicRate(Double num1, Double num2) {
        RateDto res = iDynamicRateServices.getDynamicRate(num1, num2);
        return ResponseEntity.ok(ApiDataResponse.<RateDto>builder().data(res).build());
    }

    private static class ObjectResponse extends ApiDataResponse<RateDto> {
    }

}
