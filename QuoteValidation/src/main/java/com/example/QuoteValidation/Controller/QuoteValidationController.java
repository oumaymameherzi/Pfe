package com.example.QuoteValidation.Controller;

import com.example.QuoteValidation.Entities.QuoteValidation;
import com.example.QuoteValidation.Service.QuoteValidationService;
import com.example.QuoteValidation.dto.QuoteValidationRequest;
import com.example.QuoteValidation.dto.QuoteValidationResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/quoteValidation")
public class QuoteValidationController {

    private final QuoteValidationService quoteValidationService; // ✅ Injection correcte

    @PostConstruct
    public void init() {
        log.info("🚀 Quote Validation Controller is loaded!");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<QuoteValidationResponse> getAllQuotes() {
        return quoteValidationService. getAllQuotes();
    }




    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createQuote(@RequestBody QuoteValidationRequest quoteValidationRequest) {
        quoteValidationService.createQuoteValidation(quoteValidationRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuoteValidationResponse getQuoteById(@PathVariable String id) {
        return quoteValidationService.getQuoteValidationById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateQuote(@RequestBody QuoteValidationRequest quoteRequest, @PathVariable String id) {
        QuoteValidation quoteValidation = quoteRequest.toEntity(); // Convert DTO to Entity
        quoteValidationService.updateQuoteValidation(quoteValidation, id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuote(@PathVariable String id) {
        quoteValidationService.deleteQuoteValidation(id);
    }
}
