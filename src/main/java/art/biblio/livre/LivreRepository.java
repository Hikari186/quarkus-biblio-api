package art.biblio.livre;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped 
public class LivreRepository implements PanacheRepository<Livre> {

    public List<Livre> findByAuteur(String auteur) {
        return list("auteur", auteur);
    }
}   
