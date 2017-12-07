USE daoorm01;

DROP TABLE IF EXISTS `workers_addresses` CASCADE;
DROP TABLE IF EXISTS `addresses` CASCADE;
DROP TABLE IF EXISTS `workers` CASCADE;
DROP TABLE IF EXISTS `contacts_phones` CASCADE;
DROP TABLE IF EXISTS `contacts` CASCADE;

CREATE TABLE IF NOT EXISTS `contacts` (
    id INTEGER unsigned NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    home_street1 VARCHAR(255),
    home_street2 VARCHAR(255),
    home_zipcode VARCHAR(255),
    home_city VARCHAR(255),
    home_country VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `contacts_phones` (
    id INTEGER unsigned NOT NULL AUTO_INCREMENT,
    contact_id INTEGER unsigned,
    phonenumber VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_c_cp FOREIGN KEY (contact_id)
        REFERENCES contacts(id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `workers` (
    contact_id INTEGER unsigned NOT NULL AUTO_INCREMENT,
    referrer_id INTEGER unsigned,
    jobtitle VARCHAR(255),
    PRIMARY KEY (contact_id),

    CONSTRAINT fk_c_w FOREIGN KEY (contact_id)
      REFERENCES contacts(id)
      ON DELETE CASCADE,

    CONSTRAINT fk_w_w FOREIGN KEY (referrer_id)
      REFERENCES workers(contact_id)
      ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `addresses` (
    id INTEGER unsigned unsigned NOT NULL AUTO_INCREMENT,
    street1 VARCHAR(255),
    street2 VARCHAR(255),
    zipcode VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `workers_addresses` (
    worker_id INTEGER unsigned,
    address_id INTEGER unsigned,
    PRIMARY KEY (worker_id, address_id),

    CONSTRAINT fk_a_wa FOREIGN KEY (address_id)
      REFERENCES addresses(id)
      ON DELETE CASCADE,

    CONSTRAINT fk_w_wa FOREIGN KEY (worker_id)
      REFERENCES workers(contact_id)
      ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8;
