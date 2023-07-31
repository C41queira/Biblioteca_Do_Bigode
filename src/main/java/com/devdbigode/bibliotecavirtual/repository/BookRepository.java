package com.devdbigode.bibliotecavirtual.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devdbigode.bibliotecavirtual.entity.Book;

public interface BookRepository extends MongoRepository<Book, String>{  
}
