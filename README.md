# Taxe Voirie – Espace Service

Ce projet est un **service backend Spring Boot** destiné à la **gestion des offres d’occupation temporaire d’espaces publics** dans le cadre de la taxe de voirie.

Il permet de gérer les opérations suivantes :

- ➕ Ajout d’une offre d’occupation temporaire  
- ✏️ Modification d’une offre existante  
- 🗑️ Suppression d’une offre  
- 📄 Consultation des offres

Le service expose des **API REST réactives** et peut être consommé par une application frontend (ex : Angular).

---

## 🛠️ Technologies utilisées

- Java 17  
- Spring Boot 3  
- Spring WebFlux (API REST réactive)  
- Spring Data R2DBC  
- PostgreSQL  
- Lombok  
- Jakarta Validation  
- SpringDoc OpenAPI (Swagger)  
- Maven  

---

## 🧩 Architecture

- Architecture **REST**  
- Programmation **réactive** (Mono / Flux)  
- Séparation claire :
  - Controller
  - Service
  - Repository
  - DTO / Entity

---

## ⚙️ Prérequis

Avant de lancer le projet, assure-toi d’avoir :

- Java 17  
- Maven 3.8+  
- PostgreSQL  
- Un IDE (IntelliJ IDEA recommandé)

---

## 🗄️ Configuration de la base de données

Configurer la connexion PostgreSQL dans `application.yml` ou `application.properties` :

```yaml
spring:
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/nom_database
    username: postgres
    password: password
```
## Cloner le projet
git clone https://github.com/SElmahi/taxeVoirie-Espace-Service.git

cd taxe-voirie-panneau-service

## Lancer avec Maven

mvn clean install

mvn spring-boot:run

