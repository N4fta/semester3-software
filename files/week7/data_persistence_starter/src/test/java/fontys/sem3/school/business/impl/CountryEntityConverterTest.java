package fontys.sem3.school.business.impl;

import fontys.sem3.school.domain.Country;
import fontys.sem3.school.repository.entity.CountryEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryEntityConverterTest {

    @Test
    void shouldConvertAllCountryFieldsToDomain() {
        CountryEntity countryTobeConverted = CountryEntity.builder()
                .id(1L)
                .name("Brazil")
                .code("BR")
                .build();

        Country actual = CountryConverter.convert(countryTobeConverted);

        Country expected = Country.builder()
                .id(1L)
                .name("Brazil")
                .code("BR")
                .build();

        assertEquals(expected, actual);
    }
}