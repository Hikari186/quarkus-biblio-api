package art.biblio.emprunt;

import java.time.LocalDate;

import art.biblio.livre.Livre;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity 
public class Emprunt extends PanacheEntity {

    public String nomEmprunteur;
    public LocalDate dateEmprunt;
    @ManyToOne(fetch = FetchType.LAZY)
    public Livre livre;

    protected Emprunt() {
    }

    public Emprunt(String nomEmprunteur, LocalDate dateEmprunt, Livre livre) {
        this.nomEmprunteur = nomEmprunteur;
        this.dateEmprunt = dateEmprunt;
        this.livre = livre;
    }

}
