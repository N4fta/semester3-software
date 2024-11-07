package fontys.sem3.school.repository;

import fontys.sem3.school.repository.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    CountryEntity findByCode(String code);

    @Query("select c from CountryEntity c where c.code = ?1")
    CountryEntity getCountryByCode(String code);

    @Query("select s.name from CountryEntity s where s.code = ?1")
    String getCountryNameByCode(String code);

    @Query("select s.name from CountryEntity s where s.code = ?1 and s.name = ?2")
    String getCountryNameByCodeAndName(String code, String name);

    @Query("select s.name from CountryEntity s where s.code = :countryCode")
    String getCountryNameByCodeNamedParam(@Param("countryCode") String code);

    @Query(value = "SELECT * FROM COUNTRY WHERE NAME = ?1", nativeQuery = true)
    CountryEntity getCountryByName(String name);

    @Query(value = "SELECT * FROM COUNTRY WHERE NAME = :countryName", nativeQuery = true)
    CountryEntity getCountryByNameNamedParam(@Param("countryName") String name);

}
