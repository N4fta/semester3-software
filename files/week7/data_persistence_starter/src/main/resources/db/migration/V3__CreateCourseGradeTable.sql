CREATE TABLE course_grade
(
    id         int     NOT NULL AUTO_INCREMENT,
    course_id  int     NOT NULL,
    student_id int     NOT NULL,
    grade      decimal NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (course_id, student_id),
    FOREIGN KEY (course_id) REFERENCES course (id),
    FOREIGN KEY (student_id) REFERENCES student (id)
);