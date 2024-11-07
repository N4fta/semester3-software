CREATE TABLE country
(
    id   int     NOT NULL AUTO_INCREMENT,
    code char(2) NOT NULL,
    name varchar(50),
    PRIMARY KEY (id),
    UNIQUE (code),
    UNIQUE (name)
);

CREATE TABLE student
(
    id         int NOT NULL AUTO_INCREMENT,
    pcn        int NOT NULL,
    name       varchar(50),
    country_id int,
    PRIMARY KEY (id),
    UNIQUE (pcn),
    FOREIGN KEY (country_id) REFERENCES country (id)
);