package art.biblio.livre;

import java.util.List;

import art.biblio.common.LivreIntrouvableException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LivreService {

    private final LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    @Transactional 
    public LivreResponse createLivre(LivreRequest livreRequest) {
        Livre livre = new Livre(
            livreRequest.titre(), 
            livreRequest.auteur(), 
            livreRequest.isbn(), 
            livreRequest.exemplairesDisponibles()
        );
        livreRepository.persist(livre);
        return LivreResponse.fromLivre(livre);
    }

    public LivreResponse getLivreById(Long id) {
        Livre livre = livreRepository.findById(id);
        if (livre == null) {
            throw new LivreIntrouvableException(id);
        }
        return LivreResponse.fromLivre(livre);
    }

    public List<LivreResponse> rechercher(String auteur) {
        List<Livre> livres;
        if(auteur == null || auteur.isBlank())
            livres = livreRepository.listAll();
        else 
            livres = livreRepository.findByAuteur(auteur);
            
        return livres.stream()
                    .map(LivreResponse::fromLivre)
                    .toList();
        
       
    }

}
