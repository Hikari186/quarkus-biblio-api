package art.biblio.emprunt;

import java.time.Clock;
import java.time.LocalDate;

import art.biblio.common.LivreIntrouvableException;
import art.biblio.common.PlusDExemplaireException;
import art.biblio.livre.LivreRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped 
public class EmpruntService {

    private final LivreRepository livreRepository;
    private final EmpruntRepository empruntRepository;
    private final Clock clock;

    public EmpruntService(LivreRepository livreRepository, EmpruntRepository empruntRepository, Clock clock) {
        this.livreRepository = livreRepository;
        this.empruntRepository = empruntRepository;
        this.clock = clock;
    }

    @Transactional 
    public EmpruntResponse creerEmprunt(Long livreId, EmpruntRequest empruntRequest) {
        var livre = livreRepository.findById(livreId);
        if (livre == null) {
            throw new LivreIntrouvableException(livreId);
        }
        if (livre.exemplairesDisponibles <= 0) {
            throw new PlusDExemplaireException(livre.id);
        }
        livre.exemplairesDisponibles--;

        var emprunt = new Emprunt(
            empruntRequest.nomEmprunteur(), 
            LocalDate.now(clock),
            livre
        );
        empruntRepository.persist(emprunt);
        return EmpruntResponse.fromEmprunt(emprunt);


    }

}
