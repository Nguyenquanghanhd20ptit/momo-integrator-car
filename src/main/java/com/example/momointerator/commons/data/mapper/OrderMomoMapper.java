package com.example.momointerator.commons.data.mapper;

import com.example.momointerator.commons.data.dto.momo.MomoAfterPayment;
import com.example.momointerator.commons.data.dto.momo.MomoCheckTransaction;
import com.example.momointerator.commons.data.dto.momo.OrderSendToMomo;
import com.example.momointerator.commons.data.dto.momo.ReceiveInfoMomo;
import com.example.momointerator.commons.data.request.orderMomo.OrderMomoRequest;
import com.example.momointerator.config.ResourceProperties;
import com.example.momointerator.db.mongo.model.OrderMomoModel;
import com.example.momointerator.utils.DateUtils;
import com.example.momointerator.utils.HashUtils;
import com.google.common.hash.Hashing;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

import static com.example.momointerator.commons.data.constant.momo.MomoConstant.UNPROCESSED_CODE;

@Mapper(componentModel = "spring")
public abstract class OrderMomoMapper {

    @Autowired
    private ResourceProperties resourceProperties;
    public OrderSendToMomo addValueOrderMomo(OrderMomoRequest request) {
        OrderSendToMomo orderMomo = new OrderSendToMomo();
        String requestId = HashUtils.md5(System.currentTimeMillis() + request.getOrderId());
        orderMomo.setPartnerName(resourceProperties.getMomoPartnerName());
        orderMomo.setPartnerCode(resourceProperties.getMomoPartnerCode());
        orderMomo.setRequestId(requestId);
        orderMomo.setOrderInfo(resourceProperties.getMomoOrderInfo());
        orderMomo.setRedirectUrl(request.getRedirectUrl());
        orderMomo.setIpnUrl(resourceProperties.getMomoIpnUrl());
        orderMomo.setUserInfo(request.getUserInfo());
        orderMomo.setRequestType(resourceProperties.getMomoRequestType());
        orderMomo.setExtraData("");
        orderMomo.setItems(request.getItems());
        orderMomo.setLang("vi");
        orderMomo.setAmount(request.getAmount());
        orderMomo.setOrderId(request.getOrderId());
        orderMomo.setSignature(createSignature(orderMomo));
        return orderMomo;
    }

    public OrderMomoModel toModel(OrderSendToMomo sendInfo, ReceiveInfoMomo receiveInfo){
        OrderMomoModel orderMomoModel = new OrderMomoModel()
                .setOrderId(sendInfo.getOrderId())
                .setTotalPrice(sendInfo.getAmount())
                .setSendInfo(sendInfo)
                .setReceiveInfo(receiveInfo)
                .setStatus(UNPROCESSED_CODE)
                .setCreatedAt(DateUtils.getCurrentDate());
        return orderMomoModel;
    }

    public MomoCheckTransaction toMomoCheckTransaction(OrderMomoModel request){
        MomoCheckTransaction momoCheckTransaction = new MomoCheckTransaction();
        momoCheckTransaction.setPartnerCode(request.getSendInfo().getPartnerCode());
        momoCheckTransaction.setOrderId(request.getOrderId());
        momoCheckTransaction.setRequestId(request.getSendInfo().getRequestId());
        momoCheckTransaction.setSignature(generateCheckStatusSignature(request));
        return momoCheckTransaction;
    }

    private String generateCheckStatusSignature(OrderMomoModel request) {
        String secret = resourceProperties.getMomoSecretKey();
        String key = "accessKey=" + resourceProperties.getMomoAccessKey() +
                "&orderId=" + request.getOrderId() +
                "&partnerCode=" + resourceProperties.getMomoPartnerCode() +
                "&requestId=" + request.getSendInfo().getRequestId();
        return Hashing.hmacSha256(secret.getBytes()).hashString(key, StandardCharsets.UTF_8).toString();
    }

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
