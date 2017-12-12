USE daoormjooq;

DROP TABLE IF EXISTS `entites1_entites2` CASCADE;
DROP TABLE IF EXISTS `entites2` CASCADE;
DROP TABLE IF EXISTS `entite1s_composants` CASCADE;
DROP TABLE IF EXISTS `entites1` CASCADE;

CREATE TABLE IF NOT EXISTS `entites1` (
    id INTEGER unsigned NOT NULL AUTO_INCREMENT,
    -- ... ajouter vos champs ici
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `entite1s_composants` (
    id INTEGER unsigned NOT NULL AUTO_INCREMENT,
    entite1_id INTEGER unsigned,
    -- ... ajouter vos champs ici
    PRIMARY KEY (id),
    CONSTRAINT fk_e1_c FOREIGN KEY (entite1_id)
        REFERENCES entites1(id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `entites2` (
    id INTEGER unsigned unsigned NOT NULL AUTO_INCREMENT,
    -- ... ajouter vos champs ici
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `entites1_entites2` (
    entite1_id INTEGER unsigned,
    entite2_id INTEGER unsigned,
    PRIMARY KEY (entite1_id, entite2_id),

    CONSTRAINT fk_e1e2_e2 FOREIGN KEY (entite2_id)
      REFERENCES entites2(id)
      ON DELETE CASCADE,

    CONSTRAINT fk_e1e2_e1 FOREIGN KEY (entite1_id)
      REFERENCES entites1(id)
      ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
