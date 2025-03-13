package com.example.QuoteService.dto;

import com.example.QuoteService.Entities.Quote;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Data
@Service
@Slf4j
public class QuoteMapper {
    public QuoteResponse mapToQuoteResponse(Quote quote) {
        return QuoteResponse.builder()
                .idQuotes(quote.getIdQuotes())
                .QuoteBesoin(quote.getQuoteBesoin())
                .UtilisationSystemePrecedent(quote.isUtilisationSystemePrecedent())
                .fonctionnalitesImportantes(quote.getFonctionnalitesImportantes())
                .typesSupportsUtilises(quote.getTypesSupportsUtilises())
                .defisRencontres(quote.getDefisRencontres())
                .nombreUtilisateurs(quote.getNombreUtilisateurs())
                .environnementSecurite(quote.getEnvironnementSecurite())
                .integrationPointage(quote.isIntegrationPointage())
                .importanceFaciliteUtilisation(quote.getImportanceFaciliteUtilisation())
                .preoccupationsSecurite(quote.isPreoccupationsSecurite())
                .detailsPreoccupations(quote.getDetailsPreoccupations())
                .etatQuote(quote.getEtatQuote())
                .Username(quote.getUsername())
                .dateCreation(quote.getDateCreation())
                .build();
    }
}
