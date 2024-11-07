CREATE TABLE course
(
    id          int         NOT NULL AUTO_INCREMENT,
    name        varchar(50) NOT NULL,
    description varchar(200),
    PRIMARY KEY (id),
    UNIQUE (name)
);