package com.example.QuoteValidation.Service;

import com.example.QuoteValidation.Entities.QuoteValidation;
import com.example.QuoteValidation.Repository.QuoteValidationRepository;
import com.example.QuoteValidation.dto.QuoteValidationMapper;
import com.example.QuoteValidation.dto.QuoteValidationRequest;
import com.example.QuoteValidation.dto.QuoteValidationResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class QuoteValidationService {
    private final QuoteValidationRepository quoteValidationRepository;
    private final QuoteValidationMapper quoteValidationMapper;  // ✅ Injected via constructor

    public void createQuoteValidation(QuoteValidationRequest quoteValidationRequest) {
        QuoteValidation newQuote = quoteValidationRequest.toEntity();
        quoteValidationRepository.save(newQuote);
        log.info("✅ New quote {} created", newQuote.getId());
    }

    public void updateQuoteValidation(QuoteValidation quoteValidation, String id) {
        QuoteValidation existingQuote = quoteValidationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quote Validation not found with id " + id));

        existingQuote.setQuote(quoteValidation.getQuote());
        existingQuote.setQuoteBesoin(quoteValidation.getQuoteBesoin());
        existingQuote.setUtilisationSystemePrecedent(quoteValidation.getUtilisationSystemePrecedent());
        existingQuote.setFonctionnalitesImportantes(quoteValidation.getFonctionnalitesImportantes());
        existingQuote.setTypesSupportsUtilises(quoteValidation.getTypesSupportsUtilises());
        existingQuote.setDefisRencontres(quoteValidation.getDefisRencontres());
        existingQuote.setNombreUtilisateurs(quoteValidation.getNombreUtilisateurs());
        existingQuote.setEnvironnementSecurite(quoteValidation.getEnvironnementSecurite());
        existingQuote.setIntegrationPointage(quoteValidation.getIntegrationPointage());
        existingQuote.setImportanceFaciliteUtilisation(quoteValidation.getImportanceFaciliteUtilisation());
        existingQuote.setPreoccupationsSecurite(quoteValidation.getPreoccupationsSecurite());
        quoteValidationRepository.save(existingQuote);
    }

    public List<QuoteValidationResponse> getAllQuotes() {
        List<QuoteValidation> quotesValidation = quoteValidationRepository.findAll();

        if (quotesValidation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No quotes found");
        }

        return quotesValidation.stream()
                .map(quoteValidationMapper::mapToQuoteResponse)
                .toList();
    }

    public QuoteValidationResponse getQuoteValidationById(String id) {
        return quoteValidationRepository.findById(id)
                .map(quoteValidationMapper::mapToQuoteResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "❌ Quote not found with id: " + id));
    }

    public void deleteQuoteValidation(String id) {
        if (!quoteValidationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "❌ Quote not found with id: " + id);
        }
        quoteValidationRepository.deleteById(id);
        log.info("✅ Quote {} deleted", id);
    }
}
