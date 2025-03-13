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
    private String quote;
    private String Commentaire;
    private Double PrixPropose;

}
