package com.example.QuoteService.Entities;

import com.example.QuoteService.dto.QuoteRequest;
import lombok.*;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "quotes")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    @Id
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



    public void updateFromRequest(QuoteRequest request) {
        this.setQuoteBesoin(request.getQuoteBesoin());
        this.setUtilisationSystemePrecedent(request.isUtilisationSystemePrecedent());
        this.setFonctionnalitesImportantes(request.getFonctionnalitesImportantes());
        this.setTypesSupportsUtilises(request.getTypesSupportsUtilises());
        this.setDefisRencontres(request.getDefisRencontres());
        this.setNombreUtilisateurs(request.getNombreUtilisateurs());
        this.setEnvironnementSecurite(request.getEnvironnementSecurite());
        this.setIntegrationPointage(request.isIntegrationPointage());
        this.setImportanceFaciliteUtilisation(request.getImportanceFaciliteUtilisation());
        this.setPreoccupationsSecurite(request.isPreoccupationsSecurite());
        this.setDetailsPreoccupations(request.getDetailsPreoccupations());
        this.setEtatQuote(request.getEtatQuote());
        this.setUsername(request.getUsername());
    }
}
