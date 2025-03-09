package com.example.QuoteService.Service;

import com.example.QuoteService.Entities.Quote;
import com.example.QuoteService.Repository.QuoteRepository;
import com.example.QuoteService.dto.QuoteMapper;
import com.example.QuoteService.dto.QuoteRequest;
import com.example.QuoteService.dto.QuoteResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@Data
@Service
@Slf4j
@RequiredArgsConstructor  // Remplace le constructeur manuel
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final QuoteMapper quoteMapper;  // Injection correcte

    public void createQuote(QuoteRequest quoteRequest) {
        Quote newQuote = quoteRequest.toEntity();
        quoteRepository.save(newQuote);
        log.info("✅ New quote {} created", newQuote.getIdQuotes());
    }

    public List<QuoteResponse> getAllQuotes() {
        List<Quote> quotes = quoteRepository.findAll();
        log.info("Quotes found in DB: {}", quotes.size());  // Log the number of quotes found
        if (quotes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No quotes found");
        }
        return quotes.stream()
                .map(quoteMapper::mapToQuoteResponse)
                .toList();
    }


    public QuoteResponse getQuoteById(String id) {
        return quoteRepository.findById(id)
                .map(quoteMapper::mapToQuoteResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "❌ Quote not found with id: " + id));
    }

    public void updateQuote(QuoteRequest quoteRequest, String id) {
        Quote existingQuote = quoteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "❌ Quote not found with id: " + id));

        existingQuote.updateFromRequest(quoteRequest);
        quoteRepository.save(existingQuote);
        log.info("✅ Quote {} updated", id);
    }

    public void updateQuoteStatus(String id, String etatQuote) {
        Quote existingQuote = quoteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "❌ Quote not found with id: " + id));

        existingQuote.setEtatQuote(etatQuote);
        quoteRepository.save(existingQuote);
        log.info("✅ État du devis {} mis à jour en {}", id, etatQuote);
    }


    public void deleteQuote(String id) {
        if (!quoteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "❌ Quote not found with id: " + id);
        }
        quoteRepository.deleteById(id);
        log.info("✅ Quote {} deleted", id);
    }
    public void deleteAllQuotes() {
        if (quoteRepository.count() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "❌ No quotes found to delete.");
        }
        quoteRepository.deleteAll();
        log.info("✅ All quotes deleted");
    }

    public List<QuoteResponse> getAllQuotesByEtatQuote(String etatQuote) {
        List<Quote> quotes = quoteRepository.findByEtatQuote(etatQuote);
        log.info("Quotes found in DB: {}", quotes.size());  // Log the number of quotes found
        if (quotes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No quotes found");
        }
        return quotes.stream()
                .map(quoteMapper::mapToQuoteResponse)
                .toList();
    }

    public List<QuoteResponse> getAllQuotesByUsername(String Username) {
        List<Quote> quotes = quoteRepository.findByUsername(Username);
        log.info("Quotes found in DB: {}", quotes.size());  // Log the number of quotes found
        if (quotes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No quotes found");
        }
        return quotes.stream()
                .map(quoteMapper::mapToQuoteResponse)
                .toList();
    }

}
