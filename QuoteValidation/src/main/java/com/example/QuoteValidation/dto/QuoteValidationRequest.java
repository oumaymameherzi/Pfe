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
    private String quote;
    private String Commentaire;
    private Double PrixPropose;


    public  QuoteValidation toEntity(){
        return QuoteValidation.builder()
                .quote(this.quote)
                .Commentaire(this.Commentaire)
                .PrixPropose(this.PrixPropose)
                .build();
    }
}
