package com.example.QuoteService.Controller;

import com.example.QuoteService.Service.QuoteService;
import com.example.QuoteService.dto.QuoteRequest;
import com.example.QuoteService.dto.QuoteResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/quote")
@RequiredArgsConstructor  // Remplace le constructeur manuel
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<QuoteResponse> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createQuote(@RequestBody QuoteRequest quoteRequest) {
        quoteService.createQuote(quoteRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuoteResponse getQuoteById(@PathVariable String id) {
        return quoteService.getQuoteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateQuote(@RequestBody QuoteRequest quoteRequest, @PathVariable String id) {
        quoteService.updateQuote(quoteRequest, id);
    }

    @PutMapping("/{id}/etat")
    @ResponseStatus(HttpStatus.OK)
    public void updateQuoteStatus(@PathVariable String id, @RequestBody Map<String, String> request) {
        String etatQuote = request.get("etatQuote");
        if (etatQuote == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "❌ Le champ 'etatQuote' est requis");
        }
        quoteService.updateQuoteStatus(id, etatQuote);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuote(@PathVariable String id) {
        quoteService.deleteQuote(id);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllQuotes() {
        quoteService.deleteAllQuotes();
    }

    @GetMapping("/api/quote/status/{Etat}")
    @ResponseStatus(HttpStatus.OK)
    public List<QuoteResponse> getQuoteByEtat(@PathVariable String Etat) {
        return quoteService.getAllQuotesByEtatQuote(Etat);
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<QuoteResponse> getQuoteByUsername(@PathVariable String username) {
        return quoteService.getAllQuotesByUsername(username);
    }
}
