
#  E-Shop Microservices - Projet de e-commerce distribué

Ce projet est une application e-commerce moderne développée en **Java Spring Boot** en utilisant l'architecture **microservices**.
Il permet de gérer les utilisateurs, les produits, les catégories, les commandes, l'authentification JWT, et la sécurisation via une gateway.

---

## Objectifs du Projet

- Créer une plateforme de e-commerce distribuée avec plusieurs microservices indépendants.
- Séparer les responsabilités : un service pour les utilisateurs, un pour les produits, etc.
- Utiliser **Eureka** pour la découverte de services.
- Protéger les accès via des **tokens JWT** (access & refresh tokens).
- Centraliser les appels via une **API Gateway**.

---

## Lancement du projet

###  Prérequis

- Java 17 ou plus
- Maven
- PostgreSQL 
- IDE IntelliJ 

###  Bases de données

 `eshop_jee` avec postgres.

###  Étapes

1. Cloner le projet
2. Ouvrir dans un IDE
3. Créer les bases de données nécessaires
4. Lancer les services suivants dans cet ordre :
   - `eureka-server` (registre des services)
   - `gateway` (API gateway)
   - Tous les autres services : `user-service`, `produit-service`, `commande-service`, `categorie-service`

---

## Authentification

- Auth via JWT (`access token` et `refresh token`)
- Routes sécurisées via Spring Security
- En-tête requis : `Authorization: Bearer <token>`

---

##  Microservices

| Service            | Rôle                                     |
|--------------------|------------------------------------------|
| `user-service`     | Gestion des utilisateurs et authentification |
| `produit-service`  | Gestion des produits                     |
| `categorie-service`| Gestion des catégories de produits       |
| `commande-service` | Gestion des commandes clients            |
| `eureka-server`    | Registre des microservices               |
| `gateway`          | Point d’entrée unique de l’application   |
| `home-page`        | Interface utilisateur (html)       |

---

##  Structure globale du projet

```
E-Shop-microservices/
│
├── eureka-server/         → Découverte des services
├── gateway/               → API Gateway sécurisée (JWT)
├── user-service/          → Utilisateurs & authentification
├── produit-service/       → Produits
├── categorie-service/     → Catégories
├── commande-service/      → Commandes
├── home-page/             → Front-end (probablement Angular/React)
├── pom.xml                → Build principal Maven (multi-modules)
```

Chaque service a son propre :
- `application.properties`
- Contrôleur REST (`@RestController`)
- Entités (`@Entity`)
- Repository (`extends JpaRepository`)
- Service métier (`@Service`)

