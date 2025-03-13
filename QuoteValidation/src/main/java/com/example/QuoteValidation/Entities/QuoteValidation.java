package com.example.QuoteValidation.Entities;

import com.example.QuoteValidation.Entities.FieldValidation;
import com.example.QuoteValidation.dto.QuoteValidationRequest;
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
    private String Commentaire;
    private Double PrixPropose;
    public void updateFromRequest(QuoteValidationRequest request) {
        this.setCommentaire(request.getCommentaire());
        this.setPrixPropose(request.getPrixPropose());
    }



}
