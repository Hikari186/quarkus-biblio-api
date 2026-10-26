package art.biblio.common;

public class PlusDExemplaireException extends RuntimeException {

    public PlusDExemplaireException(Long livreId) {
        super("Il n'y a plus d'exemplaires disponibles pour le livre avec l'ID " + livreId);
    }

}
