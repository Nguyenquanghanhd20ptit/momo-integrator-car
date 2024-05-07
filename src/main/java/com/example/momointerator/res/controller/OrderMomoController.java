package com.example.momointerator.res.controller;

import com.example.momointerator.commons.data.dto.momo.MomoAfterPayment;
import com.example.momointerator.commons.data.dto.momo.ReceiveInfoMomo;
import com.example.momointerator.commons.data.request.orderMomo.OrderMomoRequest;
import com.example.momointerator.service.orderMomo.CheckStatusOrderService;
import com.example.momointerator.service.orderMomo.CreateTransactionMomoService;
import com.example.momointerator.service.orderMomo.UpdateResultMomoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("")
public class OrderMomoController {
    @Autowired
    private CreateTransactionMomoService createTransaction;

    @PostMapping(value = "/create-transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Thanh toán đơn hàng bằng momo")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ReceiveInfoMomo.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> createTransaction(@RequestBody OrderMomoRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            return ResponseEntity.badRequest().build();
        }
        return createTransaction.createTransaction(request);
    }


    @Autowired
    private UpdateResultMomoService updateResultMomoService;

    @PostMapping(value = "/update-result", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "cập nhật trạng thái đơn hàng momo trả về")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> updateResult(@RequestBody  MomoAfterPayment request) {
        if (ObjectUtils.isEmpty(request)) {
            return ResponseEntity.badRequest().build();
        }
        return updateResultMomoService.updateResult(request);
    }

    @Autowired
    private CheckStatusOrderService checkStatusOrderService;
    @GetMapping("/check-status/{orderId}")
    @Operation(summary = "kiểm tra lại trạng thái giao dịch momo")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> checkStatus( @PathVariable("orderId") String orderId) {
        if (ObjectUtils.isEmpty(orderId)) {
            return ResponseEntity.badRequest().build();
        }
        return checkStatusOrderService.checkOrderMomo(orderId);
    }
}
