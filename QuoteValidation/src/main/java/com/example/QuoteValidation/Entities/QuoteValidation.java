package com.example.QuoteValidation.Entities;

import com.example.QuoteValidation.Entities.FieldValidation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.UUID;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "quotesValidation")
public class QuoteValidation {
    @Id
    String id;

    private String quote;
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
    private FieldValidation detailsPreoccupations;



}
