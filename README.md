#  Projet REST rss25SB
## 🧾 Description
Ce projet a pour but de développer et de déployer un **service RESTful** permettant de gérer des flux RSS personnalisés au format `rss25SB`, basé sur des technologies XML avancées (XSD, XSLT, XQuery...).
Le service offre une interface REST complète pour :

-  Visualiser des flux RSS au format XML ou HTML
-  Ajouter ou supprimer dynamiquement des articles
-  Rechercher des articles via des paramètres (date, catégorie)
-  Obtenir une documentation d’aide au format HTML
-  Valider les flux XML via un schéma XSD

---

##  Fonctionnalités REST

| URL                            | Méthode | Description                                  |
|--------------------------------|---------|----------------------------------------------|
| `/`                            | GET     | Page d’accueil du service                    |
| `/help`                        | GET     | Page d’aide (documentation de l’API)         |
| `/rss25SB/resume/xml`         | GET     | Liste des articles (résumé XML)              |
| `/rss25SB/resume/html`        | GET     | Liste des articles (résumé HTML)             |
| `/rss25SB/resume/xml/{id}`    | GET     | Détail d’un article (XML)                    |
| `/rss25SB/html/{id}`          | GET     | Détail d’un article (HTML)                   |
| `/rss25SB/insert`             | POST    | Ajout d’un article (XML conforme au XSD)     |
| `/rss25SB/delete/{id}`        | DELETE  | Suppression d’un article                     |
| `/rss25SB/search?...`         | GET     | Recherche par date ou catégorie (bonus)      |

---

##  Stack technique

- Java / Spring Boot
- XML (XSD, XSLT, XQuery)
- Maven
- Postman (tests)
- Déploiement sur [CleverCloud](https://www.clever-cloud.com)

---


## 🧰 Outils fournis

-  **Application de transfert** : envoi d’un fichier XML vers le service REST
-  **Application de conversion** : conversion d’un flux RSS existant vers le format `rss25SB`
  - Exemple de flux supporté : [LeMonde.fr](https://www.lemonde.fr/actualite-medias/article/2019/08/12/les-flux-rss-du-monde-fr_5498778_3236.html)

---

##  Structure du dépôt
##  Déploiement

