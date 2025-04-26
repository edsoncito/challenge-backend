package com.challenge.web.controllers;

import com.challenge.web.dtos.ApiDataResponse;
import com.challenge.web.dtos.response.HistoryCallDto;
import com.challenge.application.interfaces.IHistoryCallServices;
import com.challenge.commons.exceptions.ErrorDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "History Call", description = "get history")
public class HistoryCallController {

    private final IHistoryCallServices historyCall;

    public HistoryCallController(IHistoryCallServices historyCall) {
        this.historyCall = historyCall;
    }

    @Operation(description = "get History call", operationId = "get List History")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HistoryCallController.ObjectResponseHistory.class))),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "401", description = "Access Denied.", content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal error.", content = @Content(schema = @Schema(implementation = ErrorDetail.class)))})
    @GetMapping(path = "/history-call")
    public ResponseEntity<ApiDataResponse<List<HistoryCallDto>>> dynamicRate() {
        List<HistoryCallDto> res = historyCall.getHistoryCall();
        return ResponseEntity.ok(ApiDataResponse.<List<HistoryCallDto>>builder().data(res).build());
    }

    private static class ObjectResponseHistory extends ApiDataResponse<List<HistoryCallDto>> {
    }

}
