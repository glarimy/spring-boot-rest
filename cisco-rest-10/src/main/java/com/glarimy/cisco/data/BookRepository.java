package com.glarimy.cisco.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.glarimy.cisco.api.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {
}
