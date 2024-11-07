package fontys.sem3.school.repository;

import fontys.sem3.school.repository.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}