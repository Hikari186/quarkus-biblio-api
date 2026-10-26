package art.biblio.common;

public class LivreIntrouvableException extends RuntimeException {

    public LivreIntrouvableException(Long livreId) {
        super("Le livre avec l'ID " + livreId + " est introuvable");
    }

}
