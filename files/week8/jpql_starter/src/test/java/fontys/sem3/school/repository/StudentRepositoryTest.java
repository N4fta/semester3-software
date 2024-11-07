package fontys.sem3.school.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fontys.sem3.school.repository.entity.StudentEntity;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testFindByPcn() {
        StudentEntity student = studentRepository.findByPcn(1L);
        System.out.println("Student = " + student);
    }

    @Test
    void testGetByPcn() {
        StudentEntity student = studentRepository.getStudentByPcn(1L);
        System.out.println("Student = " + student);
    }

    @Test
    void testGetNameByPcn() {
        String name = studentRepository.getStudentNameByPcn(1L);
        System.out.println("Student.Name = " + name);
    }

    @Test
    void testGetNameByPcnNamedParam() {
        String name = studentRepository.getStudentNameByPcnNamedParam(1L);
        System.out.println("Student.Name = " + name);
    }

    @Test
    void testGetByNameNamedParam() {
        StudentEntity student = studentRepository.getStudentByNameNamedParam("Ronaldo");
        System.out.println("Student = " + student);
    }
}