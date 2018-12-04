package com.healthcarepro.healthcarepro.repository;

import com.healthcarepro.healthcarepro.model.Provider;
import org.bson.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProviderMongoRepository extends MongoRepository<Provider, Integer> {
}
