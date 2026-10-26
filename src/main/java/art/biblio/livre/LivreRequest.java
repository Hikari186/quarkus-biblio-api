package art.biblio.livre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record LivreRequest(
    @NotBlank (message = "Le titre est obligatoire") String titre, 
    @NotBlank (message = "L'auteur est obligatoire") String auteur, 
    @Pattern(regexp = "\\d{13}", message = "L'ISBN doit être une chaîne de 13 chiffres") @NotNull (message = "L'ISBN est obligatoire") String isbn, 
    @Positive (message = "Le nombre d'exemplaires disponibles doit être un entier positif") int exemplairesDisponibles
) {

}
