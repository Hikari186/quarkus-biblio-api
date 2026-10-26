package art.biblio.emprunt;

import java.time.LocalDate;

public record EmpruntResponse(
    Long id, 
    String nomEmprunteur, 
    LocalDate dateEmprunt, 
    Long livreId
) {

    public static EmpruntResponse fromEmprunt(Emprunt emprunt) {
        return new EmpruntResponse(
            emprunt.id, 
            emprunt.nomEmprunteur, 
            emprunt.dateEmprunt,
            emprunt.livre.id
        );
    }

}
