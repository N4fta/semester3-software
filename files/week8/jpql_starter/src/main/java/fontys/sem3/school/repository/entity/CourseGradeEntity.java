package fontys.sem3.school.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "course_grade")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseGradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Min(0)
    @Max(10)
    @EqualsAndHashCode.Exclude
    @Column(name = "grade")
    private Double grade;

    @NotNull
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private StudentEntity student;

}
