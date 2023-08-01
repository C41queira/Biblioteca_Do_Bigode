package com.devdbigode.bibliotecavirtual.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devdbigode.bibliotecavirtual.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{ 
}
