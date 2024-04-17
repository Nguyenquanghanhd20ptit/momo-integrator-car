package com.example.momointerator.db.mongo.model;

import com.example.momointerator.commons.data.dto.momo.OrderSendToMomo;
import com.example.momointerator.commons.data.dto.momo.ReceiveInfoMomo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Accessors(chain = true)
@Document("order_momo")
public class OrderMomoModel {
    @Id
    private String id;
    @Field("order_id")
    private String orderId;
    @Field("total_price")
    private Long totalPrice;
    @Field("send_info")
    private OrderSendToMomo sendInfo;
    @Field("receive_info")
    private ReceiveInfoMomo receiveInfo;
    @Field("created_at")
    private Date createdAt;
}
