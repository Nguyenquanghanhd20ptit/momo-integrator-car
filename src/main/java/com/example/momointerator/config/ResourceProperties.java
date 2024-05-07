package com.example.momointerator.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
public class ResourceProperties {
    private final int timeout;
    private final int redisTimeout;
    private final String redisHost;
    private final int redisPort;
    private final String redisPassword;
    private final String momoUrl;
    private final String momoCheckTransactionUrl;
    private final String momoSecretKey;
    private final String momoAccessKey;
    private final String momoPartnerCode;
    private final String momoPartnerName;
    private final String momoIpnUrl;
    private final String momoOrderInfo;
    private final String momoRequestType;
    private final String mongoUri;
    private final String mongoDatabase;
    private final String rabbitmqDirectExchange;
    private final String rabbitmqDirectRoutingStatusOrder;
    private final String rabbitmqDirectRoutingTransaction;
    private final String rabbitmqDirectQueueStatusOrder;
    private final String carModuleUrl;

    public ResourceProperties(@Value("${api.timeout}") int timeout,
                              @Value("${spring.redis.timeout}") int redisTimeout,
                              @Value("${spring.redis.host}") String redisHost,
                              @Value("${spring.redis.port}") int redisPort,
                              @Value("${spring.redis.password}") String redisPassword,
                              @Value("${momo.momo-url}") String momoUrl,
                              @Value("${momo.check-transaction-url}") String momoCheckTransactionUrl,
                              @Value("${momo.secret-key}") String momoSecretKey,
                              @Value("${momo.access-key}") String momoAccessKey,
                              @Value("${momo.partner-code}") String momoPartnerCode,
                              @Value("${momo.partner-name}") String momoPartnerName,
                              @Value("${momo.ipn-url}") String momoIpnUrl,
                              @Value("${momo.order-info}") String momoOrderInfo,
                              @Value("${momo.request-type}") String momoRequestType,
                              @Value("${spring.data.mongodb.uri}") String mongoUri,
                              @Value("${spring.data.mongodb.database}") String mongoDatabase,
                              @Value("${rabbitmq.direct-exchange}") String rabbitmqDirectExchange,
                              @Value("${rabbitmq.direct-routing.status-order}") String rabbitmqDirectRoutingStatusOrder,
                              @Value("${rabbitmq.direct-routing.transaction}") String rabbitmqDirectRoutingTransaction,
                             @Value("${rabbitmq.direct-queue.status-order}") String rabbitmqDirectQueueStatusOrder,
                              @Value("${car.module.url}") String carModuleUrl){
        this.timeout = timeout;
        this.redisTimeout = redisTimeout;
        this.redisHost = redisHost;
        this.redisPort = redisPort;
        this.redisPassword = redisPassword;
        this.momoUrl = momoUrl;
        this.momoCheckTransactionUrl = momoCheckTransactionUrl;
        this.momoSecretKey = momoSecretKey;
        this.momoAccessKey = momoAccessKey;
        this.momoPartnerCode = momoPartnerCode;
        this.momoPartnerName = momoPartnerName;
        this.momoIpnUrl = momoIpnUrl;
        this.momoOrderInfo = momoOrderInfo;
        this.momoRequestType = momoRequestType;
        this.mongoUri = mongoUri;
        this.mongoDatabase = mongoDatabase;
        this.rabbitmqDirectExchange = rabbitmqDirectExchange;
        this.rabbitmqDirectRoutingStatusOrder = rabbitmqDirectRoutingStatusOrder;
        this.rabbitmqDirectRoutingTransaction = rabbitmqDirectRoutingTransaction;
        this.rabbitmqDirectQueueStatusOrder = rabbitmqDirectQueueStatusOrder;
        this.carModuleUrl = carModuleUrl;
    }
}
