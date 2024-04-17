package com.example.momointerator.commons.data.request.orderMomo;

import com.example.momointerator.commons.data.dto.momo.ItemMomo;
import com.example.momointerator.commons.data.dto.momo.UserInfoMomo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderMomoRequest {
    private Long amount;
    private String orderId;
    private String redirectUrl;
    private UserInfoMomo userInfo;
    private List<ItemMomo> items;
}
