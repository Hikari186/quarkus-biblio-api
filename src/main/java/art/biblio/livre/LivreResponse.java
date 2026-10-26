package art.biblio.livre;

public record LivreResponse(
    Long id, 
    String titre, 
    String auteur, 
    String isbn, 
    int exemplairesDisponibles
) {

    public static LivreResponse fromLivre(Livre livre) {
        return new LivreResponse(
            livre.id, 
            livre.titre, 
            livre.auteur, 
            livre.isbn, 
            livre.exemplairesDisponibles
        );
    }

}
