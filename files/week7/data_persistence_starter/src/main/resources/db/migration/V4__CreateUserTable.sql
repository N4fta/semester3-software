CREATE TABLE `users`
(
    id         int         NOT NULL AUTO_INCREMENT,
    username   varchar(20) NOT NULL,
    password   varchar(50) NOT NULL,
    student_id int         NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username),
    FOREIGN KEY (student_id) REFERENCES student (id)
);