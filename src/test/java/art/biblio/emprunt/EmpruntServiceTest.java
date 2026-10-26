package art.biblio.emprunt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import art.biblio.common.LivreIntrouvableException;
import art.biblio.common.PlusDExemplaireException;
import art.biblio.livre.Livre;
import art.biblio.livre.LivreRepository;

@ExtendWith(MockitoExtension.class)
class EmpruntServiceTest {

    @Mock
    private LivreRepository livreRepository;

    @Mock
    private EmpruntRepository empruntRepository;

    private final Clock clock = Clock.fixed(Instant.parse("2030-01-01T10:00:00Z"), ZoneId.of("Europe/Paris"));

    private EmpruntService empruntService;

    @BeforeEach
    void setUp() {
        empruntService = new EmpruntService(livreRepository, empruntRepository, clock);
    }

    @Test
    void creerEmprunt_livreInexistant_leveLivreIntrouvable() {
        when(livreRepository.findById(99L)).thenReturn(null);

        assertThrows(LivreIntrouvableException.class, () ->
                empruntService.creerEmprunt(99L, new EmpruntRequest("Dupont")));

        verify(empruntRepository, never()).persist(any(Emprunt.class));
    }

    @Test
    void creerEmprunt_plusDExemplaire_levePlusDExemplaire() {
        var livre = new Livre("Germinal", "Zola", "9782253004226", 0);
        when(livreRepository.findById(1L)).thenReturn(livre);

        assertThrows(PlusDExemplaireException.class, () ->
                empruntService.creerEmprunt(1L, new EmpruntRequest("Dupont")));

        verify(empruntRepository, never()).persist(any(Emprunt.class));
    }

    @Test
    void creerEmprunt_exemplaireDisponible_decrementeEtEnregistre() {
        var livre = new Livre("Germinal", "Zola", "9782253004226", 3);
        livre.id = 1L;
        when(livreRepository.findById(1L)).thenReturn(livre);

        var response = empruntService.creerEmprunt(1L, new EmpruntRequest("Dupont"));

        assertEquals(2, livre.exemplairesDisponibles);
        assertEquals("Dupont", response.nomEmprunteur());
        assertEquals(LocalDate.of(2030, 1, 1), response.dateEmprunt());
        assertEquals(1L, response.livreId());
        verify(empruntRepository).persist(any(Emprunt.class));
    }
}
