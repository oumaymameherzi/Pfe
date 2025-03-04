package com.example.QuoteValidation.Entities;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldValidation {
    private String fieldName;
    private boolean isAccepted;
    private String refusalReason;
}
