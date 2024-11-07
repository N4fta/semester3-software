package fontys.sem3.school.repository;

import fontys.sem3.school.repository.entity.CountryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;
    
    @Test
    void testFindByCode() {
        CountryEntity country = countryRepository.findByCode("BR");
        System.out.println("country = " + country);
    }
    
    @Test
    void testGetCountryByCode() {
        CountryEntity country = countryRepository.getCountryByCode("BR");
        System.out.println("country = " + country);
    }

    @Test
    void testGetCountryNameByCode() {
        String country = countryRepository.getCountryNameByCode("NL");
        System.out.println("country = " + country);
    }

    @Test
    void testGetCountryNameByCodeAndName() {
        String country = countryRepository.getCountryNameByCodeAndName("NL", "Netherlands");
        System.out.println("country = " + country);
    }

    @Test
    void testGetCountryNameByCodeNamedQuery() {
        String country = countryRepository.getCountryNameByCodeNamedParam("NL");
        System.out.println("country = " + country);
    }

    @Test
    void testGetCountryByName() {
        CountryEntity country = countryRepository.getCountryByName("Romania");
        System.out.println("country = " + country);
    }

    @Test
    void testGetCountryByNameNamedParam() {
        CountryEntity country = countryRepository.getCountryByNameNamedParam("Romania");
        System.out.println("country = " + country);
    }

}