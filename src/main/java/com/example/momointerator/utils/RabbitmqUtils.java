package com.example.momointerator.utils;

import com.example.momointerator.config.ResourceProperties;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqUtils {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private ResourceProperties resourceProperties;

    public void pushMessageUpdateOrder(Object message){
        amqpTemplate.convertAndSend(resourceProperties.getRabbitmqDirectExchange(),
                resourceProperties.getRabbitmqDirectRoutingStatusOrder(),message);
    }
    public void  pushMessageToRoutingKey(String routingKey , Object message){
        amqpTemplate.convertAndSend(resourceProperties.getRabbitmqDirectExchange(),
                routingKey,message);
    }
}
