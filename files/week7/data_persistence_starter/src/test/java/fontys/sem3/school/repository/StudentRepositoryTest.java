package fontys.sem3.school.repository;

import fontys.sem3.school.repository.entity.CountryEntity;
import fontys.sem3.school.repository.entity.StudentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import jakarta.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void save_shouldSaveStudentWithAllFields() {
        CountryEntity brazil = saveCountry("Brazil", "BR");
        StudentEntity student = StudentEntity.builder().pcn(111L).name("Ronaldo Nazario").country(brazil).build();
        StudentEntity savedStudent = studentRepository.save(student);
        assertNotNull(savedStudent.getId());
        savedStudent = entityManager.find(StudentEntity.class, savedStudent.getId());
        StudentEntity expectedStudent = StudentEntity.builder().id(savedStudent.getId()).pcn(111L)
                .name("Ronaldo Nazario").country(brazil).build();
        assertEquals(expectedStudent, savedStudent);
    }

    private CountryEntity saveCountry(String name, String code) {
        CountryEntity country = CountryEntity.builder().name(name).code(code).build();
        entityManager.persist(country);
        return country;
    }
}