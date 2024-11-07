package fontys.sem3.school.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MaxCourseGrades {

    private CourseEntity course;
    private Double maxGrade;
}
