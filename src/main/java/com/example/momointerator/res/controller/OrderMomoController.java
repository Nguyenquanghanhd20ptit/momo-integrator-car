package com.example.momointerator.res.controller;

import com.example.momointerator.commons.data.request.orderMomo.OrderMomoRequest;
import com.example.momointerator.service.orderMomo.CreateTransactionMomoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("")
public class OrderMomoController {
    private CreateTransactionMomoService createTransaction;
    @PostMapping(value = "/create-transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Thanh toán đơn hàng bằng momo")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> createTransaction(OrderMomoRequest request){
        if(ObjectUtils.isEmpty(request)){
            return ResponseEntity.badRequest().build();
        }
        return createTransaction.createTransaction(request);
    }
}
