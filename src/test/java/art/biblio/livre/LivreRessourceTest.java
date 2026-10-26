package art.biblio.livre;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
class LivreRessourceTest {

    @Test
    void createLivre_valide_renvoie201AvecLocation() {
        given()
            .contentType(ContentType.JSON)
            .body("""
                {"titre":"Germinal","auteur":"Zola","isbn":"9782253004226","exemplairesDisponibles":2}
                """)
        .when()
            .post("/api/livres")
        .then()
            .statusCode(201)
            .header("Location", containsString("/api/livres/"))
            .body("titre", is("Germinal"))
            .body("exemplairesDisponibles", is(2));
    }

    @Test
    void createLivre_invalide_renvoie400() {
        given()
            .contentType(ContentType.JSON)
            .body("""
                {"titre":"","auteur":"Zola","isbn":"123","exemplairesDisponibles":0}
                """)
        .when()
            .post("/api/livres")
        .then()
            .statusCode(400)
            .body("$", hasKey("violations"));
    }

    @Test
    void getLivreById_inexistant_renvoie404() {
        given()
        .when()
            .get("/api/livres/999")
        .then()
            .statusCode(404)
            .body("status", is(404))
            .body("message", containsString("999"));
    }

    @Test
    void rechercherLivres_parAuteur_renvoieSesLivres() {
        given()
            .contentType(ContentType.JSON)
            .body("""
                {"titre":"Les Misérables","auteur":"Hugo","isbn":"9782070409228","exemplairesDisponibles":1}
                """)
        .when()
            .post("/api/livres")
        .then()
            .statusCode(201);

        given()
            .queryParam("auteur", "Hugo")
        .when()
            .get("/api/livres")
        .then()
            .statusCode(200)
            .body("titre", is(List.of("Les Misérables")));
    }
}
