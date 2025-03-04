package com.example.QuoteValidation.dto;

import com.example.QuoteValidation.Entities.FieldValidation;
import com.example.QuoteValidation.Entities.QuoteValidation;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class QuoteValidationRequest {
    String quote;
    private FieldValidation QuoteBesoin;
    private FieldValidation UtilisationSystemePrecedent;
    private FieldValidation fonctionnalitesImportantes;
    private FieldValidation typesSupportsUtilises;
    private FieldValidation defisRencontres;
    private FieldValidation nombreUtilisateurs;
    private FieldValidation environnementSecurite;
    private FieldValidation integrationPointage;
    private FieldValidation importanceFaciliteUtilisation;
    private FieldValidation preoccupationsSecurite;


    public  QuoteValidation toEntity(){
        return QuoteValidation.builder()
                .quote(this.quote)
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
                .build();
    }
}
