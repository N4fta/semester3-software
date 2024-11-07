package fontys.sem3.school.repository;

import fontys.sem3.school.repository.entity.MaxCourseGrades;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseGradeRepositoryTest {

    @Autowired
    private CourseGradeRepository courseGradeRepository;

    @Test
    void testGetMaxGradePerCourse() {
        List<MaxCourseGrades> maxes = courseGradeRepository.getMaxGradePerCourse();
        maxes.forEach(tuple -> {
            System.out.println(tuple.getCourse().getName() + " - " + tuple.getMaxGrade());
        });
    }

    @Test
    void testGetAverageGradePerCourse() {
        List<MaxCourseGrades> averages = courseGradeRepository.getAverageGradePerCourse();
        averages.forEach(tuple -> {
            System.out.println(tuple.getCourse().getName() + " - " + tuple.getMaxGrade());
        });
    }
}