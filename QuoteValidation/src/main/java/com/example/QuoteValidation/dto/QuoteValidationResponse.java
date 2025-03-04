package com.example.QuoteValidation.dto;


import com.example.QuoteValidation.Entities.FieldValidation;
import lombok.*;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class QuoteValidationResponse {
    String id;
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

}
