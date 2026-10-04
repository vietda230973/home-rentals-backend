# Home Rentals Backend

Backend Spring Boot pour une application de location immobilière ("home rentals").

Le projet expose des APIs REST pour la gestion des biens à louer, les messages et l'authentification utilisateur, avec une base de données MySQL et une documentation OpenAPI/Swagger.

## Stack technique

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Spring Security + JWT (OAuth2 Resource Server)
- MySQL
- MapStruct
- Lombok
- Springdoc OpenAPI (Swagger UI)

## Structure du projet

```text
home-rentals-backend/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/fr/vietda/rentals/home/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── mapper/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── HomeApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/fr/vietda/rentals/home/
├── HELP.md
├── README.md
├── pom.xml
├── mvnw
├── mvnw.cmd
└── .gitignore
```

## Fonctionnalités

- Gestion des rentals (liste, détail, création, mise à jour)
- Upload d'image pour les annonces immobilières
- Authentification et sécurité JWT
- API documentée via Swagger UI
- Persistance JPA sur MySQL

## Prérequis

- Java 21
- Maven 3.6+
- MySQL local

## Configuration

Le fichier `src/main/resources/application.properties` contient la configuration principale :

```properties
spring.application.name=home
spring.datasource.url=jdbc:mysql://localhost:3306/rentals
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
server.port=9090
jwt.secret=v9y$B&E)H@McQfTjWmZq4t7w!z%C*F-JaNdRgUkXp2s5u8x/A?D(G+KbPeShVmYq
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

Assurez-vous d'avoir une base MySQL nommée `rentals` accessible localement.

## Démarrage

### Lancer les tests

```bash
./mvnw test
```

### Démarrer l'application

```bash
./mvnw spring-boot:run
```

L'application démarre par défaut sur le port :

```text
http://localhost:9090
```

### Swagger UI

```text
http://localhost:9090/swagger-ui.html
```

## Points d'entrée principaux

- `/api/rentals` : gestion des locations
- `/api/auth/**` : endpoints d'authentification
- `/v3/api-docs` : spécification OpenAPI
- `/swagger-ui.html` : interface Swagger UI

## Notes

- Les images uploadées pour les rentals sont stockées sous `uploads/rentals/`.
- L'application utilise des routes sécurisées avec JWT et autorise explicitement les endpoints d'authentification et Swagger.

## Exemple de commande pour créer un rental

```bash
curl -X POST "http://localhost:9090/api/rentals/create" \
  -F "name=Appartement central" \
  -F "surface=45" \
  -F "price=1200" \
  -F "description=Appartement de 45m2 au centre-ville" \
  -F "userId=1" \
  -F "picture=@/path/to/image.jpg"
```

## Contribution

Ce dépôt est un backend Java Spring Boot de démonstration / prototype. Les contributions sont les bienvenues si vous souhaitez ajouter des tests, sécuriser davantage les endpoints ou améliorer l'architecture.
