package com.devdbigode.bibliotecavirtual.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.devdbigode.bibliotecavirtual.entity.Book;

public interface BookRepository extends MongoRepository<Book, String>{

    @Query("{$or: [{'title': { $regex: ?0, $options: 'i' }} ,{'theme': { $regex: ?0, $options: 'i' }}]}")
    List<Book> searchBooks(String text);

    @Query("{$and: [{ 'price': {$gte: ?0}}, { 'price': {$lte: ?1}}]}")
    List<Book> searchPrice(Double minPrice, Double maxPrice);
}
