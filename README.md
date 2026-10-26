# API de bibliothèque (Quarkus)

Un petit projet Quarkus que j'ai réalisé pour découvrir le framework, après avoir fait une API équivalente en Spring Boot (réservation de vols).

L'idée : on peut ajouter des livres, les consulter, et emprunter un livre tant qu'il reste des exemplaires disponibles.

## Technologies

- Java 21
- Quarkus 3 (Quarkus REST, Hibernate ORM avec Panache, Hibernate Validator, SmallRye OpenAPI)
- H2 (base de données en mémoire)
- JUnit, Mockito et RestAssured pour les tests
- Maven

## Lancer le projet

```bash
./mvnw quarkus:dev
```
