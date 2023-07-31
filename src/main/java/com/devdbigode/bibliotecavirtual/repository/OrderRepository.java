package com.devdbigode.bibliotecavirtual.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devdbigode.bibliotecavirtual.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{
}
