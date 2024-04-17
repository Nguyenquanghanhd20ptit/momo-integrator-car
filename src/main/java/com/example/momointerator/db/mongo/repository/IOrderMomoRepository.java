package com.example.momointerator.db.mongo.repository;

import com.example.momointerator.db.mongo.model.OrderMomoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderMomoRepository extends MongoRepository<OrderMomoModel,String> {

}
