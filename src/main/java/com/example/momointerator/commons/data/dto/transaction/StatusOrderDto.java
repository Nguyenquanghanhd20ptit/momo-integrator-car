package com.example.momointerator.commons.data.dto.transaction;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatusOrderDto {
    private String orderId;
    private Integer statusCode;
    private String transactionType;
}
