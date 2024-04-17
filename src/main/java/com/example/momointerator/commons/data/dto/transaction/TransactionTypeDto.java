package com.example.momointerator.commons.data.dto.transaction;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TransactionTypeDto {
    private String orderId;
    private String tranTypeId;
    private String transactionId;
}
