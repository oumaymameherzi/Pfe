package com.example.QuoteService.dto;

import com.example.QuoteService.Entities.DefisRencontres;
import com.example.QuoteService.Entities.FonctionnalitesImportantes;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class QuoteResponse {
    private String idQuotes;
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
    private String EtatQuote;
    private String Username;
}
