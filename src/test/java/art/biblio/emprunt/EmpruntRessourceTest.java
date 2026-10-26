package art.biblio.emprunt;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
class EmpruntRessourceTest {

    @Test
    void creerEmprunt_dernierExemplaire_renvoie201PuisDeuxiemeEmprunt409() {
        Integer livreId = given()
            .contentType(ContentType.JSON)
            .body("""
                {"titre":"Le Petit Prince","auteur":"Saint-Exupéry","isbn":"9782070612758","exemplairesDisponibles":1}
                """)
        .when()
            .post("/api/livres")
        .then()
            .statusCode(201)
            .extract().path("id");

        given()
            .contentType(ContentType.JSON)
            .body("""
                {"nomEmprunteur":"Dupont"}
                """)
        .when()
            .post("/api/livres/" + livreId + "/emprunts")
        .then()
            .statusCode(201)
            .body("nomEmprunteur", is("Dupont"))
            .body("livreId", is(livreId));

        given()
            .contentType(ContentType.JSON)
            .body("""
                {"nomEmprunteur":"Martin"}
                """)
        .when()
            .post("/api/livres/" + livreId + "/emprunts")
        .then()
            .statusCode(409)
            .body("status", is(409));
    }

    @Test
    void creerEmprunt_livreInexistant_renvoie404() {
        given()
            .contentType(ContentType.JSON)
            .body("""
                {"nomEmprunteur":"Dupont"}
                """)
        .when()
            .post("/api/livres/999/emprunts")
        .then()
            .statusCode(404);
    }

    @Test
    void creerEmprunt_nomVide_renvoie400() {
        given()
            .contentType(ContentType.JSON)
            .body("""
                {"nomEmprunteur":""}
                """)
        .when()
            .post("/api/livres/1/emprunts")
        .then()
            .statusCode(400);
    }
}
