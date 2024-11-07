package fontys.sem3.school.repository;

import fontys.sem3.school.repository.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    StudentEntity findByPcn(Long pcn);

    @Query("select s from StudentEntity s where s.pcn = ?1")
    StudentEntity getStudentByPcn(Long pcn);

    @Query("select s.name from StudentEntity s where s.pcn = ?1")
    String getStudentNameByPcn(Long pcn);

    @Query("select s.name from StudentEntity s where s.pcn = :pcn")
    String getStudentNameByPcnNamedParam(@Param("pcn") Long pcn);

    @Query(value = "SELECT * FROM STUDENT WHERE NAME = :name", nativeQuery = true)
    StudentEntity getStudentByNameNamedParam(@Param("name") String name);
}
