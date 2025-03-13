package com.example.QuoteValidation.Repository;

import com.example.QuoteValidation.Entities.QuoteValidation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteValidationRepository extends MongoRepository<QuoteValidation,String> {
    QuoteValidation findByQuote(String quote);
}
