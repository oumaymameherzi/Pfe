package com.example.QuoteService.Entities;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DefisRencontres {
    private boolean pannesFrequente;
    private boolean tempsAttente;
    private boolean difficulteVisiteurs;
    private boolean gestionComplique;
}
