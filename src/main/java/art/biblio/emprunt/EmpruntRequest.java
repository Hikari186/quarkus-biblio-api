package art.biblio.emprunt;


import jakarta.validation.constraints.NotBlank;

public record EmpruntRequest(
    @NotBlank (message = "Le nom de l'emprunteur est obligatoire") String nomEmprunteur
) {

}
