# Devops-POC

## Introduction 

Ce répertoire contient le code source pour le projet 10 Openclassroom de la formation d'Architecte Logiciel. 

**Étudiante : Samantha Deschaepmeester**

Ce projet concerne l'entreprise Medhead qui a besoin de réaliser une preuve de concept (Proof of concept) pour son nouveau système d'invervention d'urgence.

## Objectifs 

La plateforme a les objectifs suivants : 
- Permettre de regrouper et d'unifier les pratiques des divers acteurs du consortium.
- Pallier les risques liés au traitement des recommandations de lits d'hôpitaux dans des situations d'intervention d'urgence.
- La plateforme devra toujours être disponible.

## Emplacement des fichiers 

Le projet est séparé en plusieurs sections : 

| Nom du dossier | Description |
| - | - |
| **xx** | xx |

## Fonctionnement

Pour faire fonctionner le projet, suivez ces instructions :
1. Cloner le projet.
2. Installer les dépendances.
3. xxx

Backend : 
1. Develop a functionnality on a new branch 
2. Open a pull request and review the changes of the branch 
3. Merge the request on main 
-> It will trigger Github Actions with the workflow file (backend.yml)
-> Then it will create a docker image on dockerhub 
-> Then do the following to deploy it on heroku

heroku login
heroku container:login
docker pull deschaepmeesters/backend:latest
docker tag deschaepmeesters/backend:latest registry.heroku.com/medhead-backend/web
docker push registry.heroku.com/medhead-backend/web
heroku container:release web --app medhead-backend
