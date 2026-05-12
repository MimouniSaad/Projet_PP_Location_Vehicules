<h1 align="center"> Projet Module Principes de programmation </h1>
<h1 align="center"> Projet REST API : Application de location de véhicules </h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white" />
  <img src="https://img.shields.io/badge/JWT-Security-black?style=for-the-badge&logo=jsonwebtokens" />
  <img src="https://img.shields.io/badge/Angular-19-DD0031?style=for-the-badge&logo=angular&logoColor=white" />
</p>

<h1 align="center"> AutoLoc — Système de Gestion de Location de Véhicules</h1>

---

## Objectif du Projet

Ce projet a pour objectif de concevoir et développer une **application web complète basée sur une architecture REST API**, dédiée à la gestion d'une entreprise de location de voitures et de camions.

L'application expose un ensemble d'**endpoints REST sécurisés par JWT**, consommés par un frontend Angular, et déployée dans des **conteneurs Docker**. Elle couvre l'intégralité du cycle métier de la location : de l'inscription du client jusqu'à la clôture du contrat, en passant par la validation des réservations, le traitement des paiements, la génération de factures PDF et le suivi de la maintenance du parc automobile.

---

## Description

**AutoLoc** est une plateforme de gestion interne et client pour une entreprise de location de véhicules. Elle repose sur une architecture **Spring Boot en couches** =  **(Models → DTOs → Repository → Services → Controller)** côté backend, et une interface **Angular** côté frontend.

Le système distingue quatre types d'utilisateurs aux responsabilités bien définies :

- Un **client** peut parcourir le catalogue, effectuer des réservations et régler ses paiements en ligne.
- Un **administrateur** gère le parc de véhicules, valide ou refuse les réservations, enregistre les retours et supervise les paiements.
- Un **super administrateur** dispose des mêmes droits que l'admin, avec en plus la capacité de créer et gérer d'autres comptes administrateurs.
- Un **téchnicien** reçoit et traite les ordres de maintenance assignés par l'admin.

L'ensemble des données est stocké dans une base **MySQL** dont le schéma est versionné avec **Flyway** et mapping objet-relationel avec **JPA et Hibernate**, et le projet est entièrement contenarisé avec **Docker** et orchestré avec **Docker-compose** .

---

## Staff Technique

| Marouane ZAID | 
| Saad Mimouni | 
| Rayane HARKATI |

---

## Stack technique:
- **Langages de programmation** : Java 
- **Framework Back** :  Spring Boot
- **Framework Front** : Angular
- **ORM** : JPA, Hibernate
- **Base de Données** : MySQL 8
- **Conteunarisation** : Docker

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white" />
  <img src="https://img.shields.io/badge/Angular-19-DD0031?style=for-the-badge&logo=angular&logoColor=white" />
</p>

---

## Méthode de travail

Le projet suit une organisation **GitFlow** structurée autour de trois environnements distincts :

| Environnement | Branche | Rôle |
|:---:|:---:|:---|
| **Développement** | `feature/<ticket>` | Développement d’une nouvelle fonctionnalité |
| **Correction** | `fix/<ticket>` | fix d’un bug ou comportement incorrect |
| **Refactorisation** | `refactor/<ticket>` | Amélioration interne du code sans modification fonctionnelle |
| **Pré-production** | `develop` | Intégration, validation et tests |
| **Production** | `main` | Version stable déployée |

<img width="1741" height="805" alt="image" src="https://github.com/user-attachments/assets/c122e440-ecd7-4aa1-bcef-adfb5b6bb14a" />

---

### Gestion des tâches

La répartition du travail repose sur un système de **tickets GitHub Issues**, chaque fonctionnalité ou correction se trace individuellement avec un label (`enhancement`, `bug`, etc.) et assignée à un membre de l'équipe.

<img width="1757" height="727" alt="image" src="https://github.com/user-attachments/assets/ecfa0a26-5a79-4644-ab12-82c292701de8" />

Chacun des membres de l'équipe — **Marouane**, **Saad** et **Rayane** — suit le flux de travail suivant :

1. **Création d’un ticket GitHub Issues** avec description et labels
2. **Création d’une branche dédiée** selon la nature du travail :
   - `feature/<ticket>` → nouvelle fonctionnalité
   - `fix/<ticket>` → correction de bug
   - `refactor/<ticket>` → amélioration ou restructuration du code
3. **Développement et commits** sur la branche dédiée
4. **Ouverture d’une Pull Request vers `develop`** pour revue et validation
5. **Tests et intégration** en environnement de pré-production
6. **Merge vers `main`** uniquement après validation finale
7. **Clôture du ticket** et suppression de la branche associée 

Cette approche garantit une traçabilité complète de chaque partie avec des **commits**, pour éviter les conflits entre développeurs et assurer un seul code validé dans la branche de production.

---

## Conception et Modélisation

Nous avons conçu les diagrammes de classes, de cas d'utilisations ainsi qu'un modèle de base de données en respectant les relations d'entités suivantes : 

- **Many-to-One**
- **One-to-One**
- **Many-to-Many**

  
Vous pouvez y accéder au dossier de conceptions via le lien  : [Conceptions](Conceptions)

Diagramme de classes : [Diagramme Classes](Conceptions/diagramme%20classes.pdf) 

<img width="1028" height="840" alt="image" src="https://github.com/user-attachments/assets/8f225086-aeff-427c-be9f-9e83874222f0" />

Diagramme des cas d'utilisations : [Diagramme Use Cases](Conceptions/diagramme%20use%20cases.pdf)

<img width="911" height="825" alt="image" src="https://github.com/user-attachments/assets/a8c5d311-f77b-4440-9231-f76189390ec8" />

Modèle de base de données :  [Modèle Base de Données](Conceptions/car_location_database.pdf) 

<img width="909" height="818" alt="database" src="https://github.com/user-attachments/assets/a0d2b799-deac-4144-8443-976f70ab1b55" />

---
## Contenarisation avec Docker et orchéstration docker-compose

### docker-compose
Accédez au **docker-compose.yaml** : [Docker_Compose](docker-compose.yaml) 
### Dockerfile backend
Accédez au **Dockerfile** du backend : [Docker_File](autoloc/Dockerfile) 
### Dockerfile frontend
Accédez au **Dockerfile** du frontend : [Docker_File](autoloc-front/Dockerfile) 

---

## Lancer l'application avec docker-compose

### Prérequis
- Docker Desktop
  
Installation :
https://www.docker.com/products/docker-desktop/

### Étapes de lancement du projet

**1 — Cloner le projet**
```bash
git clone https://github.com/MimouniSaad/Projet_PP_Location_Vehicules.git
cd autoloc
```

**2 — Lancer tous les services**
```bash
docker compose up
```

**3 — Accéder aux services**

| Service | URL | Description |
|:---|:---|:---|
| Frontend Angular | `http://localhost:80` | Interface utilisateur |
| Swagger UI  | `http://localhost:8080/swagger-ui.html` | Swagger |
| API Backend | `http://localhost:8080` | API REST Spring Boot |
| phpMyAdmin | `http://localhost:8081` | Administration base de données et tests des données |
| MySQL | `localhost:3307` | Base de données |

**4 — Arrêter les services**
```bash
docker compose down
```

**5 — Arrêter et supprimer les données**
```bash
docker compose down -v
```

### Volume Docker

Les données MySQL sont **persistées** dans un volume Docker nommé `mysql_data`, donc elles restent conservées lors du redémarrage des contenaires.

```bash
# Voir les volumes existants
docker volume ls

# Supprimer le volume
docker volume rm autoloc_mysql_data
```

### Publication Docker Hub

Les images Docker du projet sont disponibles publiquement sur Docker Hub : [hub.docker.com/u/rayanehr](https://hub.docker.com/u/rayanehr)

| Service | Image |
|:---|:---|
| Backend | [`rayanehr/projet_pp_location_vehicules-backend`](https://hub.docker.com/r/rayanehr/projet_pp_location_vehicules-backend) |
| Frontend | [`rayanehr/projet_pp_location_vehicules-frontend`](https://hub.docker.com/r/rayanehr/projet_pp_location_vehicules-frontend) |

### Construction des images

```bash
# 1 Build images (Front & Back)
docker build -t rayanehr/projet_pp_location_vehicules-backend:latest ./autoloc
docker build -t rayanehr/projet_pp_location_vehicules-frontend:latest ./autoloc-front

# 2 Se connecter à Docker Hub
docker login

# 3 Push images
docker push rayanehr/projet_pp_location_vehicules-backend:latest
docker push rayanehr/projet_pp_location_vehicules-frontend:latest
```

### Récupération des images en local

```bash
# Backend
docker pull rayanehr/projet_pp_location_vehicules-backend:latest
docker run -p 8080:8080 rayanehr/projet_pp_location_vehicules-backend:latest
```

```bash
# Frontend
docker pull rayanehr/projet_pp_location_vehicules-frontend:latest
docker run -p 80:80 rayanehr/projet_pp_location_vehicules-frontend:latest
```

---

## Architecture du Projet

### Architecture Spring

### Rôle de chaque couche

| Couche | Responsabilité |
|:---|:---|
| **`Controller`** | Reçoit la requête HTTP, valide le DTO, appelle le Service, retourne `ResponseEntity` |
| **Service** | Logique métier complète : règles, calculs, coordination des Repositories |
| **Repository** | Unique point de contact avec la BDD — requêtes JPA/Hibernate |
| **Mapper** | Conversion Entité ↔ DTO via MapStruct (zéro boilerplate) |
| **Model** | Entités JPA mappées aux tables MySQL |
| **Security** | Intercepte chaque requête, valide le JWT, injecte l'utilisateur dans le contexte Spring |
| **DTO** | Objets légers échangés avec le frontend — protège les entités |
| **Exception** | Gestion centralisée des erreurs — retourne des réponses HTTP claires |

### Structure des packages et arborescence

```
autoloc/
└── src/
├── main/
│   ├── java/com/autoloc/
│   │   ├── config/
│   │   │   ├── DataInitializer.java
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   └── SecurityConfig.java
│   │   │
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── ClientController.java
│   │   │   ├── MaintenanceController.java
│   │   │   ├── NotificationController.java
│   │   │   ├── PaiementController.java
│   │   │   ├── ReservationController.java
│   │   │   ├── TechnicienController.java
│   │   │   └── VehiculeController.java
│   │   │
│   │   ├── dto/
│   │   │   ├── ChangePasswordRequest.java
│   │   │   ├── ClientRequest.java
│   │   │   ├── ClientResponse.java
│   │   │   ├── JwtResponse.java
│   │   │   ├── LoginRequest.java
│   │   │   ├── MaintenanceRequest.java
│   │   │   ├── MaintenanceResponse.java
│   │   │   ├── NotificationRequest.java
│   │   │   ├── NotificationResponse.java
│   │   │   ├── PaiementRequest.java
│   │   │   ├── PaiementResponse.java
│   │   │   ├── RegisterRequest.java
│   │   │   ├── ReservationRequest.java
│   │   │   ├── ReservationResponse.java
│   │   │   ├── TechnicienRequest.java
│   │   │   ├── TechnicienResponse.java
│   │   │   ├── UpdateProfilRequest.java
│   │   │   ├── VehiculeRequest.java
│   │   │   └── VehiculeResponse.java
│   │   │
│   │   ├── enums/
│   │   │   ├── categoriePermis.java
│   │   │   ├── modePaiement.java
│   │   │   ├── paysEmission.java
│   │   │   ├── statutMaintenance.java
│   │   │   ├── statutPaiement.java
│   │   │   ├── statutReservation.java
│   │   │   ├── statutVehicule.java
│   │   │   └── userRole.java
│   │   │
│   │   ├── exception/
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   ├── MaintenanceNotFoundException.java
│   │   │   ├── TechnicienNotFoundException.java
│   │   │   ├── UserNotFoundException.java
│   │   │   └── VehiculeNotFoundException.java
│   │   │
│   │   ├── mapper/
│   │   │   ├── ClientMapper.java
│   │   │   ├── MaintenanceMapper.java
│   │   │   ├── NotificationMapper.java
│   │   │   ├── PaiementMapper.java
│   │   │   ├── ReservationMapper.java
│   │   │   ├── TechnicienMapper.java
│   │   │   └── VehiculeMapper.java
│   │   │
│   │   ├── model/
│   │   │   ├── Admin.java
│   │   │   ├── Camion.java
│   │   │   ├── Client.java
│   │   │   ├── Notification.java
│   │   │   ├── Option.java
│   │   │   ├── OrdreMaintenance.java
│   │   │   ├── Paiement.java
│   │   │   ├── PermisConduire.java
│   │   │   ├── Reservation.java
│   │   │   ├── SuperAdmin.java
│   │   │   ├── Technicien.java
│   │   │   ├── User.java
│   │   │   ├── Vehicule.java
│   │   │   └── Voiture.java
│   │   │
│   │   ├── repository/
│   │   │   ├── ClientRepository.java
│   │   │   ├── MaintenanceRepository.java
│   │   │   ├── NotificationRepository.java
│   │   │   ├── OptionRepository.java
│   │   │   ├── PaiementRepository.java
│   │   │   ├── ReservationRepository.java
│   │   │   ├── TechnicienRepository.java
│   │   │   ├── UserRepository.java
│   │   │   └── VehiculeRepository.java
│   │   │
│   │   ├── security/
│   │   │   ├── JwtFilter.java
│   │   │   └── JwtUtil.java
│   │   │
│   │   ├── service/
│   │   │   ├── AuthService.java
│   │   │   ├── ClientService.java
│   │   │   ├── MaintenanceService.java
│   │   │   ├── NotificationService.java
│   │   │   ├── PaiementService.java
│   │   │   ├── ReservationService.java
│   │   │   ├── TechnicienService.java
│   │   │   └── VehiculeService.java
│   │   │
│   │   └── AutolocApplication.java
│   │
│   └── resources/
│       ├── db.migration/
│       │   └── V1__create_users.sql
│       ├── application.properties
│       └── schema.sql
│
└── test/
└── java/com/autoloc/
└── service/
├── AuthServiceTest.java
├── MaintenanceServiceTest.java
├── NotificationServiceTest.java
├── PaiementServiceTest.java
├── ReservationServiceTest.java
├── TechnicienServiceTest.java
└── VehiculeServiceTest.java
```

---

## Endpoints REST principaux

```
POST   /api/auth/register           Inscription client
POST   /api/auth/login              Connexion → JWT
POST   /api/auth/admin              Créer un compte admin

GET    /api/vehicules               Liste les véhicules
POST   /api/vehicules               Ajouter un véhicule       [ADMIN]
PUT    /api/vehicules/{id}          Modifier un véhicule      [ADMIN]
DELETE /api/vehicules/{id}          Supprimer un véhicule     [ADMIN]

POST   /api/reservations                Créer une réservation      [CLIENT]
GET    /api/reservations                Lister les réservations    [ADMIN]

PATCH  /api/reservations/{id}/valider   Valider                    [ADMIN]
PATCH  /api/reservations/{id}/refuser   Refuser                    [ADMIN]
PATCH  /api/reservations/{id}/retour    Enregistrer retour         [ADMIN]

POST   /api/maintenance             Créer ordre maintenance   [ADMIN]

GET    /api/clients                 Liste des clients         [CLIENT]
POST   /api/clients                 Créer un client           [CLIENT]
```

## Tests des endpoints

Les endpoints ont été testés directement via le **frontend Angular**, en conditions réelles d'utilisation.

| Scénario testé |
|:---|
| Inscription d'un nouveau client |
| Connexion et récupération du token JWT |
| Affichage du catalogue véhicules |
| Création d'une réservation |
| Annulation d'une réservation |
| Ajout d'un véhicule |
| Déclenchement d'une maintenance |
| Assignation d'un technicien |

---


### Données insérées automatiquement


Le projet utilise un `DataInitializer` qui s'exécute automatiquement au démarrage de Spring Boot.

Lors du lancement des images **Docker**, les données sont insérées automatiquement dans la base.

Les données incluent également des véhicules, réservations, paiements, ordres de maintenance et notifications.


### Comptes de test

| Rôle | Email | Mot de passe |
|---|---|---|
| Super Admin | superadmin@autoloc.fr | Admin@1234 |
| Admin | ahmed.benali@autoloc.fr | Admin@1234 |
| Admin | sara.morin@autoloc.fr | Admin@1234 |
| Technicien | karim.mansouri@autoloc.fr | Tech@1234 |
| Technicien | youssef.hamidi@autoloc.fr | Tech@1234 |
| Client | marie.dupont@gmail.com | Client@1234 |
| Client | omar.zaidi@gmail.com | Client@1234 |

---

## Tests unitaires

### Outils utilisés

| Outil | Rôle |
|:---|:---|
| **JUnit 5** | Framework de tests — structure et exécution des tests |
| **Mockito** | Simulation des dépendances (Repository, JwtUtil, PasswordEncoder...) |

### Lancement des tests

```bash
cd autoloc
mvn test
```

### Couverture des tests

| Fichier de test | Nb tests | Cas testés |
|:---|:---:|:---|
| `AuthServiceTest` | 3 | Login réussi, utilisateur introuvable, mauvais mot de passe |
| `VehiculeServiceTest` | 6 | findById, findAll, suppression LOUE/EN_MAINTENANCE, changerStatut, introuvable |
| `ReservationServiceTest` | 7 | Création, véhicule déjà réservé, valider, valider non EN_ATTENTE, refuser, findAll, getByClient |
| `MaintenanceServiceTest` | 7 | Déclencher, assigner, technicien non disponible, ordre non SIGNALE, résoudre, résoudre non EN_COURS, clôturer |
| `PaiementServiceTest` | 6 | Effectuer paiement, réservation non confirmée, déjà payée, rembourser, rembourser non confirmé, getPaiement introuvable |
| `TechnicienServiceTest` | 7 | Créer, email déjà utilisé, supprimer, supprimer avec ordres EN_COURS, introuvable, findAll, findDisponibles |
| `NotificationServiceTest` | 4 | Envoyer, utilisateur introuvable, findByUtilisateurId, liste vide |


### Rôle des tests unitaires dans le projet

Rôles des tests unitaires :

- **Vérifier la logique métier** — chaque règle est testée indépendamment
- **Détecter les régressions** — si une modification casse une fonctionnalité existante, le test échoue immédiatement

---

## Analyse des Besoins

### Besoins fonctionnels

#### Authentification et gestion des comptes
- Un visiteur peut consulter les véhicules disponibles et s'inscrire lui-même en tant que client
- Un client et un admin peuvent se connecter via email et mot de passe — un token JWT est retourné
- Un admin peut créer manuellement un compte client ou un compte téchnicien
- Seul le super administrateur peut créer un compte administrateur
- Le compte super administrateur est initialisé automatiquement au premier démarrage de l'application — il ne peut pas être créé depuis l'interface web
- Tout utilisateur authentifié peut modifier son profil et changer son mot de passe

#### Gestion des clients
- L'admin peut consulter la liste des clients, voir le détail d'un client, modifier ses informations et désactiver ou supprimer son compte
- Chaque client possède un permis de conduire avec sa catégorie (B, BE, C1, C, CE…) qui est vérifié automatiquement lors d'une réservation — un client avec un permis B ne peut pas louer un camion

#### Gestion des véhicules
- L'admin peut ajouter, modifier et supprimer des voitures et des camions
- Chaque véhicule possède un statut : DISPONIBLE, LOUE, EN_MAINTENANCE ou HORS_SERVICE
- Les véhicules peuvent avoir des options (GPS, climatisation, siège bébé, hayon…) — relation Many-to-Many avec la table `vehicule_option`
- L'admin peut signaler une panne sur un véhicule, ce qui crée automatiquement un ordre de maintenance et passe le véhicule en EN_MAINTENANCE

#### Gestion des réservations
- Un client peut réserver un véhicule pour une période donnée, modifier ou annuler sa réservation
- Le système vérifie automatiquement la disponibilité du véhicule et la validité du permis avant de créer la réservation
- Le montant total est calculé automatiquement (durée × prix par jour)
- L'admin valide ou refuse chaque réservation — le client est notifié automatiquement
- L'admin enregistre le retour du véhicule avec la date réelle — des frais de retard sont calculés automatiquement en cas de retard

#### Gestion des paiements
- Un client peut régler sa réservation confirmée en ligne
- Chaque réservation peut inclure une ou plusieurs assurances (responsabilité civile, vol, bris de glace…) — relation Many-to-Many avec la table `reservation_assurance`
- Une facture PDF est générée et envoyée par email automatiquement après chaque paiement confirmé
- L'admin peut confirmer manuellement un paiement en espèces ou déclencher un remboursement

#### Gestion de la maintenance
- L'admin crée un ordre de maintenance et assigne un technicien disponible
- Le téchnicien reçoit une notification, démarre la réparation et la clôture en renseignant le coût réel
- À la clôture, le véhicule repasse automatiquement en statut DISPONIBLE

#### Notifications
- Chaque événement métier déclenche une notification : confirmation de réservation, validation, refus, paiement reçu, retour enregistré, ordre de maintenance assigné
- Les notifications sont stockées en base et envoyées par email

### Besoins non fonctionnels

- | **Sécurité** | Spring Security + JWT, BCrypt pour les mots de passe, @PreAuthorize par rôle |
- | **Validation** | @Valid sur tous les DTOs entrants, contraintes CHECK en base de données |
- | **Portabilité** | Conteneurisation complète Docker Compose |
- | **Traçabilité** | Flyway pour les migrations SQL, timestamps sur toutes les entités |
- | **Maintenabilité** | Architecture en couches, séparation entité/DTO avec MapStruct |
- | **Documentation** | Swagger UI auto-générée depuis les annotations Spring |

---

<p align="center">
  Projet académique — Principe de programmation / Développement Web
</p>

