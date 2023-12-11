package com.example.asterixapi;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface AsterixRepository extends MongoRepository<Asterix,String> {
}
