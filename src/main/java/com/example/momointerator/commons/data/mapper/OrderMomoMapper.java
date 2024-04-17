package com.example.momointerator.commons.data.mapper;

import com.example.momointerator.commons.data.dto.momo.OrderSendToMomo;
import com.example.momointerator.commons.data.dto.momo.ReceiveInfoMomo;
import com.example.momointerator.commons.data.request.orderMomo.OrderMomoRequest;
import com.example.momointerator.config.ResourceProperties;
import com.example.momointerator.db.mongo.model.OrderMomoModel;
import com.example.momointerator.utils.DateUtils;
import com.example.momointerator.utils.HashUtils;
import com.example.momointerator.utils.MomoUtils;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public abstract class OrderMomoMapper {

    @Autowired
    private ResourceProperties resourceProperties;
    @Autowired
    private MomoUtils momoUtils;
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
        orderMomo.setSignature(momoUtils.createSignature(orderMomo));
        orderMomo.setLang("vi");
        orderMomo.setAmount(request.getAmount());
        orderMomo.setOrderId(request.getOrderId());
        return orderMomo;
    }

    public OrderMomoModel toModel(OrderSendToMomo sendInfo, ReceiveInfoMomo receiveInfo){
        OrderMomoModel orderMomoModel = new OrderMomoModel()
                .setOrderId(sendInfo.getOrderId())
                .setTotalPrice(sendInfo.getAmount())
                .setSendInfo(sendInfo)
                .setReceiveInfo(receiveInfo)
                .setCreatedAt(DateUtils.getCurrentDate());
        return orderMomoModel;
    }
}
