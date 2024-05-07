package com.example.momointerator.service.orderMomo;

import com.example.momointerator.commons.data.dto.transaction.StatusOrderDto;
import com.example.momointerator.db.mongo.model.OrderMomoModel;
import com.example.momointerator.db.mongo.repository.IOrderMomoRepository;
import com.example.momointerator.db.rabbitmq.producer.RabbitmqProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import static com.example.momointerator.commons.data.constant.OrderConstant.*;
import static com.example.momointerator.commons.data.constant.TransactionConstant.MOMO_CODE;
import static com.example.momointerator.commons.data.constant.momo.MomoConstant.*;

@Component
public class OrderMomoComponent {
    @Autowired
    private RabbitmqProducer rabbitmqProducer;
    @Autowired
    private IOrderMomoRepository orderMomoRepository;
    @Autowired
    private MongoOperations mongoOperations;
    Boolean updateResult(String orderId, Integer resultCode) {
        try {
           Boolean update =  updateStatusById(orderId,resultCode);
           if(!update) return false;
           else {
               if (resultCode.equals(RESULT_SUCCESS_CODE)) {
                   return updateStatusOrder(orderId, ORDER_PAID_CODE);
               } else if (resultCode.equals(RESULT_CANCELED_CODE)) {
                   return   updateStatusOrder(orderId, ORDER_CANCELED_CODE);
               } else if (resultCode.equals(RESULT_WAIT_PAYMENT_CODE)) {
                   return  updateStatusOrder(orderId, ORDER_WAIT_PAYMENT_CODE);
               } else {
                   return updateStatusOrder(orderId,ORDER_PAID_FAIL_CODE);
               }
           }
        }catch (Exception e){
            e.printStackTrace();;
            return false;
        }
    }
    private Boolean updateStatusById(String orderId, Integer status) {
        try {
            Query query = new Query(Criteria.where("order_id").is(orderId));
            Update update = new Update().set("status",status);
            mongoOperations.updateFirst(query, update, OrderMomoModel.class);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    private Boolean updateStatusOrder(String orderId, Integer statusCode) {
        try {
            StatusOrderDto request = new StatusOrderDto()
                    .setOrderId(orderId)
                    .setTransactionId(MOMO_CODE)
                    .setStatusCode(statusCode);
            rabbitmqProducer.pushMessageUpdateOrder(request);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
