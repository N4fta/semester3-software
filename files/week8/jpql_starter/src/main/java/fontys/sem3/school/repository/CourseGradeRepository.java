package fontys.sem3.school.repository;

import fontys.sem3.school.repository.entity.CourseGradeEntity;
import fontys.sem3.school.repository.entity.MaxCourseGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseGradeRepository extends JpaRepository<CourseGradeEntity, Long> {

    @Query("SELECT new fontys.sem3.school.repository.entity.MaxCourseGrades(c.course, MAX(c.grade)) "
            + "FROM CourseGradeEntity AS c "
            + "GROUP BY c.course")
    List<MaxCourseGrades> getMaxGradePerCourse();

    @Query("SELECT new fontys.sem3.school.repository.entity.MaxCourseGrades(c.course, AVG(c.grade)) "
            + "FROM CourseGradeEntity AS c "
            + "GROUP BY c.course")
    List<MaxCourseGrades> getAverageGradePerCourse();
}
