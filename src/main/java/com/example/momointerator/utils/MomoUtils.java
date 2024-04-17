package com.example.momointerator.utils;

import com.example.momointerator.commons.data.dto.momo.OrderSendToMomo;
import com.example.momointerator.config.ResourceProperties;
import com.google.common.hash.Hashing;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MomoUtils {
    @Autowired
    private ResourceProperties resourceProperties;

    public String createSignature(OrderSendToMomo order) {
        String extraData = "";
        String secret = resourceProperties.getMomoSecretKey();
        String key = "accessKey=" + resourceProperties.getMomoAccessKey() +
                "&amount=" +  order.getAmount() +
                "&extraData=" + extraData +
                "&ipnUrl=" +  order.getIpnUrl()+
                "&orderId=" + order.getOrderId() +
                "&orderInfo=" + order.getOrderInfo() +
                "&partnerCode=" + order.getPartnerCode() +
                "&redirectUrl=" + order.getRedirectUrl() +
                "&requestId=" + order.getRequestId() +
                "&requestType=" + order.getRequestType();
        return Hashing.hmacSha256(secret.getBytes()).hashString(key, StandardCharsets.UTF_8).toString();
    }
}
