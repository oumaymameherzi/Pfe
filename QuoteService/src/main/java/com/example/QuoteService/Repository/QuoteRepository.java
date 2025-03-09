package com.example.QuoteService.Repository;

import com.example.QuoteService.Entities.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends MongoRepository<Quote, String> {
    List<Quote> findByEtatQuote(String etatQuote);

    List<Quote> findByQuoteBesoin(String quoteBesoin);

    List<Quote> findByUtilisationSystemePrecedent(boolean utilisationSystemePrecedent);

    List<Quote> findByTypesSupportsUtilises(String typesSupportsUtilises);

    List<Quote> findByUsername(String username);


}
