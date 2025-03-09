package com.example.QuoteService.Entities;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FonctionnalitesImportantes {
    private boolean authentificationEmpreintes;
    private boolean lectureCartesRFID;
    private boolean codesPIN;
    private boolean rapportAcces;
    private boolean notificationsAcces;
}
