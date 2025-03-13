package com.example.QuoteService.dto;

import com.example.QuoteService.Entities.DefisRencontres;
import com.example.QuoteService.Entities.FonctionnalitesImportantes;
import com.example.QuoteService.Entities.Quote;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuoteRequest {
    private String QuoteBesoin;
    private boolean UtilisationSystemePrecedent;
    private FonctionnalitesImportantes fonctionnalitesImportantes;
    private String typesSupportsUtilises;
    private DefisRencontres defisRencontres;
    private String nombreUtilisateurs;
    private String environnementSecurite;
    private boolean integrationPointage;
    private String importanceFaciliteUtilisation;
    private boolean preoccupationsSecurite;
    private String detailsPreoccupations;
    private String etatQuote;
    private String Username;
    private LocalDateTime dateCreation;

    public Quote toEntity() {
        return Quote.builder()
                .QuoteBesoin(this.QuoteBesoin)
                .UtilisationSystemePrecedent(this.UtilisationSystemePrecedent)
                .fonctionnalitesImportantes(this.fonctionnalitesImportantes)
                .typesSupportsUtilises(this.typesSupportsUtilises)
                .defisRencontres(this.defisRencontres)
                .nombreUtilisateurs(this.nombreUtilisateurs)
                .environnementSecurite(this.environnementSecurite)
                .integrationPointage(this.integrationPointage)
                .importanceFaciliteUtilisation(this.importanceFaciliteUtilisation)
                .preoccupationsSecurite(this.preoccupationsSecurite)
                .detailsPreoccupations(this.detailsPreoccupations)
                .etatQuote(this.etatQuote)
                .Username(this.Username)
                .dateCreation(LocalDateTime.now())
                .build();
    }
}
