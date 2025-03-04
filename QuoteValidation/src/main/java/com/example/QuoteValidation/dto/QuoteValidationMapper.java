package com.example.QuoteValidation.dto;

import com.example.QuoteValidation.Entities.QuoteValidation;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Data
@Service
@Slf4j

public class QuoteValidationMapper {

    public QuoteValidationResponse mapToQuoteResponse(QuoteValidation quote) {
        return QuoteValidationResponse.builder()
                .id(quote.getId())
                .quote(quote.getQuote()) // Correction ici
                .QuoteBesoin(quote.getQuoteBesoin())
                .UtilisationSystemePrecedent(quote.getUtilisationSystemePrecedent())
                .fonctionnalitesImportantes(quote.getFonctionnalitesImportantes())
                .typesSupportsUtilises(quote.getTypesSupportsUtilises())
                .defisRencontres(quote.getDefisRencontres())
                .nombreUtilisateurs(quote.getNombreUtilisateurs())
                .environnementSecurite(quote.getEnvironnementSecurite())
                .integrationPointage(quote.getIntegrationPointage())
                .importanceFaciliteUtilisation(quote.getImportanceFaciliteUtilisation())
                .preoccupationsSecurite(quote.getPreoccupationsSecurite())
                .build();
    }
}

