package fontys.sem3.school.configuration.db;

import fontys.sem3.school.repository.CountryRepository;
import fontys.sem3.school.repository.CourseGradeRepository;
import fontys.sem3.school.repository.CourseRepository;
import fontys.sem3.school.repository.StudentRepository;
import fontys.sem3.school.repository.entity.CountryEntity;
import fontys.sem3.school.repository.entity.CourseEntity;
import fontys.sem3.school.repository.entity.CourseGradeEntity;
import fontys.sem3.school.repository.entity.StudentEntity;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseDataInitializer {

    private CountryRepository countryRepository;

    private CourseGradeRepository courseGradeRepository;

    private StudentRepository studentRepository;

    private CourseRepository courseRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {

        CountryEntity countryNL = CountryEntity.builder().code("NL").name("Netherlands").build();
        CountryEntity countryBG = CountryEntity.builder().code("BG").name("Bulgaria").build();
        CountryEntity countryRO = CountryEntity.builder().code("RO").name("Romania").build();
        CountryEntity countryBR = CountryEntity.builder().code("BR").name("Brazil").build();
        CountryEntity countryCN = CountryEntity.builder().code("CN").name("China").build();

        if (countryRepository.count() == 0) {
            countryRepository.save(countryNL);
            countryRepository.save(countryBG);
            countryRepository.save(countryRO);
            countryRepository.save(countryBR);
            countryRepository.save(countryCN);
        }

        StudentEntity studentRonaldo = StudentEntity.builder().id(1L).pcn(1L).name("Ronaldo").country(countryBR).build();
        StudentEntity studentMike = StudentEntity.builder().id(2L).pcn(2L).name("Mike").country(countryNL).build();
        StudentEntity studentRomario = StudentEntity.builder().id(3L).pcn(3L).name("Romario").country(countryBR).build();
        StudentEntity studentJohn = StudentEntity.builder().id(4L).pcn(4L).name("John").country(countryRO).build();

        if (studentRepository.count() == 0) {
            studentRepository.save(studentRonaldo);
            studentRepository.save(studentMike);
            studentRepository.save(studentRomario);
            studentRepository.save(studentJohn);
        }

        CourseEntity courseS1 = CourseEntity.builder().id(1L).name("S1").description("Semester 1").build();
        CourseEntity courseS2 = CourseEntity.builder().id(2L).name("S2").description("Semester 2").build();
        CourseEntity courseS3 = CourseEntity.builder().id(3L).name("S3").description("Semester 3").build();
        CourseEntity courseS4 = CourseEntity.builder().id(4L).name("S4").description("Semester 4").build();

        if (courseRepository.count() == 0) {
            courseRepository.save(courseS1);
            courseRepository.save(courseS2);
            courseRepository.save(courseS3);
            courseRepository.save(courseS4);
        }

        CourseGradeEntity gradeRonaldoS1 = CourseGradeEntity.builder().id(1L).grade(8.5).course(courseS1).student(studentRonaldo).build();
        CourseGradeEntity gradeRonaldoS2 = CourseGradeEntity.builder().id(2L).grade(7.5).course(courseS2).student(studentRonaldo).build();
        CourseGradeEntity gradeRonaldoS3 = CourseGradeEntity.builder().id(3L).grade(9.5).course(courseS3).student(studentRonaldo).build();
        CourseGradeEntity gradeMikeS1 = CourseGradeEntity.builder().id(4L).grade(7.0).course(courseS1).student(studentMike).build();
        CourseGradeEntity gradeMikeS2 = CourseGradeEntity.builder().id(5L).grade(8.0).course(courseS2).student(studentMike).build();
        CourseGradeEntity gradeMikeS3 = CourseGradeEntity.builder().id(6L).grade(9.0).course(courseS3).student(studentMike).build();
        CourseGradeEntity gradeRomarioS1 = CourseGradeEntity.builder().id(7L).grade(6.0).course(courseS1).student(studentRomario).build();
        CourseGradeEntity gradeRomarioS2 = CourseGradeEntity.builder().id(8L).grade(5.0).course(courseS2).student(studentRomario).build();
        CourseGradeEntity gradeRomarioS3 = CourseGradeEntity.builder().id(9L).grade(7.0).course(courseS3).student(studentRomario).build();

        if (courseGradeRepository.count() == 0) {
            courseGradeRepository.save(gradeRonaldoS1);
            courseGradeRepository.save(gradeRonaldoS2);
            courseGradeRepository.save(gradeRonaldoS3);
            courseGradeRepository.save(gradeMikeS1);
            courseGradeRepository.save(gradeMikeS2);
            courseGradeRepository.save(gradeMikeS3);
            courseGradeRepository.save(gradeRomarioS1);
            courseGradeRepository.save(gradeRomarioS2);
            courseGradeRepository.save(gradeRomarioS3);
        }
    }
}
