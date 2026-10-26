package art.biblio.livre;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Livre extends PanacheEntity {

    public String titre;
    public String auteur;
    public String isbn;
    public int exemplairesDisponibles;

    protected Livre() {
    }

    public Livre(String titre, String auteur, String isbn, int exemplairesDisponibles) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.exemplairesDisponibles = exemplairesDisponibles;
    }

}
