package com.example.momointerator.db.mongo.repository;

import com.example.momointerator.db.mongo.model.OrderMomoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderMomoRepository extends MongoRepository<OrderMomoModel,String> {
    @Query("{order_id : ?0}")
    Optional<OrderMomoModel> findByOrderId(String orderId);
}
