package com.example.momointerator.commons.data.dto.momo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderSendToMomo {
    private String partnerName;
    private String partnerCode;
    private String requestId;
    private Long amount;
    private String orderId;
    private String orderInfo;
    private String redirectUrl;
    private String ipnUrl;
    private String requestType;
    private String extraData;
    private UserInfoMomo userInfo;
    private List<ItemMomo> items;
    private String lang;
    private String signature;
}
