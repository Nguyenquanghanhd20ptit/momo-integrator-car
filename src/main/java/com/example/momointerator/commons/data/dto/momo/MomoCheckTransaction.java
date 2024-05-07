package com.example.momointerator.commons.data.dto.momo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MomoCheckTransaction {
    private String partnerCode;
    private String requestId;
    private String orderId;
    private String lang = "en";
    private String signature;
}
