
# TP construire ses DAO avec jOOQ

L'objectif du TP est d'utiliser jOOQ pour écrire des DAO de lecture et écriture de données en utilisant les classes que jOOQ aura généré à partir d'un schéma de base de donnée (approche "bottom-up", ou "orientée BDD").

La documentation de l'API de jOOQ est [https://www.jooq.org/doc/3.3/manual/sql-building/sql-statements/](ici).

## Initialiser l'utilisateur et la BDD du TP

**À ne faire qu'une fois** pour créer l'utilisateur MySQL et sa base dédiée au TP.

* se connecter au client MySQL avec l'utilisateur MySQL root:

```bash
mysql -u root -p
```

* créer l'utilisateur et sa base :

```sql
CREATE USER "daoormjooquser"@"localhost";
SET password FOR "daoormjooquser"@"localhost" = password('daoormjooqpwd');
CREATE DATABASE daoormjooq;
GRANT ALL ON daoormjooq.* TO "daoormjooquser"@"localhost";
exit;
```

## Définir un modèle métier

Décider d'un modèle métier répondant au schéma de tables suivant :

![](src/main/resources/img/modélisations_tables.png)

où :

* `entites1` est la table stockant vos entités principales (des contacts par exemple)
* `entite1s_composants` est la table des composants multiples associés à un enregistrement d'`entites1` (les numéros de téléphone d'un contact). S'agissant d'une composition, la suppression d'une `entité1` doit supprimer en cascade les `composants` associés
* `entites2` est la table stockant vos entités secondaires (des adresses postales par exemple)
* `entites1_entites2` est la table d'associations multiples entre vos `entites1` et `entites2` (les différentes adresses professionnelles d'un contact par exemple). S'agissant de simples associations, la suppression d'une `entité1` ou d'une `entité2` doit supprimer les associations qui les concernent, mais pas l'`entité` associée à l'autre bout.

## Construire les tables correspondants à votre métier

Dans `src/main/resources/db/create_database_structure.sql`, stocker le script permettant de réinitialiser les tables de la base de données. Vous pouvez adapter le fichier `create_database_structure-sample.sql` mais utilisez des noms de tables correspondants à votre modèle métier. Modifiez le nom de la base de données utilisée à la première ligne du script si besoin.

Les tables correspondants à `entites1`, `entite1s_composants` et `entites2` doivent avoir au moins 2 champs d'information (en plus de ceux concernant les clefs).

Exécutez le script pour construire les tables de vos données :
* soit grâce au plugin `sql-maven-plugin` avec la commande `mvn sql:execute@reset-db`
* OU via le client MySQL `mysql -u daoormjooquser -p < src/main/resources/db/create_database_structure.sql` (adaptez avec utilisateur MySQL)

## Générer les classes avec jOOQ

Le projet Maven est pré-configuré pour interagir avec une BDD MySQL. Repérez dans le `pom.xml` :
* les dépendances relatives à jOOQ et au pilote de BDD utilisé
* les sous-balises `<jdbc.*>...</jdbc.*>` de `<properties>` définissent des variables relatives à la connexion à la BDD. Adaptez les valeurs à votre projet.
* le plugin `jooq-codegen-maven` s'occupe de générer les classes jOOQ en pré-condition de la phase Maven `compile` (`mvn compile`). Elles seront ainsi disponibles lorsque Maven compilera votre code
* la balise `<generator>.<configuration>.<target>` définit la génération des classes :
  * le sous/répertoire `target/generated-sources/jooq`
  * les classes seront dans le package `com.mydigitalschool.dao_orm.daojooq.dao`, et seront donc accessibles dans le répertoire `src/main.../daojooq.dao` du projet

Exploration du code généré :
* Daoormjooq contient les informations du schéma utilisé. Il expose en singletons les tables
* `Tables` expose les mêmes tables
* le répertoire `tables` expose les mêmes tables et les DTO représentant la structure des enregistrements pour chacunes des tables

## Écrire un DAO pour l'Entité1

### Insertion

Le fichier `src/main/resources/application.yml` contient la configuration SpringBoot du projet, notamment les propriétés permettant d'instancier la `DataSource` injectée dans vos DAO. Vérifiez que ses caractéristiques correspondent à votre base de données.

`Application.elaborationDaoEntity1Insert` vous permet de tester l'enregistrement d'une Entity1 et de s'assurer qu'un identifiant a été renseigné. Compléter `Entity1Dao.saveEntity1(...)` pour réaliser l'insertion en base :
* en ignorant les composants liés dans un premier temps de développement. Vérifiez que vous avez bien des données insérées en base
* en insérant les composants liés ensuite avec le `ComponentDao.saveComponents()` qui sera appelé par `AppController.saveEntity1`. Vérifiez bien que vos données liées sont insérées, et que leurs identifiants sont bien renseignés également dans le `Entity1` renvoyé.

Utilisez les 2 syntaxes d'insertion différentes, par exemple pour :

* `Entity1Dao.saveEntity1(...)` avec un DTO :

```java
Entites1Record entity1Record = dbDSL.newRecord(ENTITES1);
entity1Record.setProperty1(entity1.property1);
entity1Record.setProperty2(entity1.property2);
// ...
entity1Record.store();
entity1.id = entity1Record.getId().longValue();
```

* `ComponentsDao.saveComponent(...)` sans DTO :

```java
dbDSL.insertInto(Components1, Componants.COMPONENTS.PROPERTY1, Componants.COMPONENTS.PROPERTY2)
  .values(component.property1, component.property2)
```


Ignorez l'insertion des `Entity2` associés pour l'instant.

Si Eclipse ne donne pas accès aux classes générées, il y a plusieurs solutions pour rajouter `target/generated-sources/jooq` au classpath :
* corriger la configuration du plugin M2Eclipse comme indiqué [dans ce post](https://stackoverflow.com/a/1192638)
* modifier le fichier `.classpath` généré par Eclipse à la racine du projet (voir [ce post](https://stackoverflow.com/a/7772131)) pour y rajouter `target/generated-sources/jooq`

### Lecture

De la même manière, implémenter `Entity1Dao.getEntity1(...)`, la méthode d'appel dans le contrôleur et sa méthode de test `Application.elaborationDaoEntity1Select`. Vérifiez que les composants liés sont bien là.

### Suppression

Implémenter les méthodes de DAO, contrôleur et de test pour la suppression d'un `Entity1` par son identifiant. Vérifiez en base que les composants liés sont supprimés en cascade.

## Écrire un DAO pour l'Entité2

Implémentez l'insertion et la lecture d'une `Entité2`.

## Finaliser le contrôleur

Afin de gérer les associations `Entity1` <-> `Entity2`, `AppController.saveEntity1` doit désormais utiliser les 2 Dao développés pour :
* insérer `Entity1`
* insérer `Entity2` si son identifiant n'est pas déjà défini
* insérer l'association `Entity1` <-> `Entity2`

## Bonus

Implémenter `AppController.getEntities1(T property1, int nbValuesToSkip, int nbValuesToReturn)` qui permet de renvoyer une liste d'entités1 de façon paginée, selon un critère de recherche `ENTITES1.PROPERTY1.contains(property1)` par l'utilisation des clauses [WHERE](https://www.jooq.org/doc/3.3/manual/sql-building/sql-statements/select-statement/where-clause/) et [LIMIT](https://www.jooq.org/doc/3.3/manual/sql-building/sql-statements/select-statement/limit-clause/).
