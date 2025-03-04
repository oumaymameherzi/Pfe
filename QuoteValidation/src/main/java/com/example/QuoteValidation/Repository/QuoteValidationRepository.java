package com.example.QuoteValidation.Repository;

import com.example.QuoteValidation.Entities.QuoteValidation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteValidationRepository extends MongoRepository<QuoteValidation,String> {
}
