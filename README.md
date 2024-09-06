# ConstructionXpert Services - Microservices

## Description
ConstructionXpert Services est une application de gestion de projets de construction construite avec une architecture microservices pour assurer une meilleure évolutivité, maintenabilité et résilience. Chaque microservice gère une partie distincte de l'application, et les services communiquent via des API REST sécurisées.

## Microservices Identifiés
- **API Gateway Service** : Point d’entrée unique pour toutes les requêtes. Utilise **Spring Cloud Gateway**.
- **Discovery Service** : Permet la découverte automatique des services. Utilise **Eureka**.
- **Service de Gestion des Utilisateurs** : Gestion de l’authentification et l’autorisation des utilisateurs.
- **Service de Gestion des Projets** : Gestion des projets de construction.
- **Service de Gestion des Tâches** : Gestion des tâches associées aux projets.
- **Service de Gestion des Ressources** : Gestion des ressources (personnel, matériel, etc.).

## Architecture
L'application utilise une architecture basée sur les microservices pour permettre une meilleure scalabilité et résilience. Chaque service est indépendant et interagit avec les autres via des API REST.

### Technologies Principales
- **Java 17** : Langage de programmation principal
- **Spring Boot** : Framework utilisé pour la construction des microservices
- **Spring Cloud Gateway** : Pour la gestion des API Gateway
- **Eureka** : Pour le service de découverte des microservices
- **OpenFeign** : Pour la communication entre microservices
- **Docker** : Pour le déploiement en conteneurs
- **Resilience4j** : Pour le Circuit Breaker

## Prérequis
- **Java 17** ou plus
- **Maven** pour la gestion des dépendances
- **Docker** pour le conteneurisation et déploiement
- **Eureka Server** pour la découverte des microservices

## Installation et Démarrage
1. Clonez le repository :
   ```bash
   git clone https://github.com/votre-utilisateur/constructionxpert-services.git
   cd constructionxpert-services
