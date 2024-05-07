package com.example.momointerator.db.rabbitmq.producer;

import com.example.momointerator.config.ResourceProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqProducer {
    @Autowired
    private RabbitTemplate amqpTemplate;
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
