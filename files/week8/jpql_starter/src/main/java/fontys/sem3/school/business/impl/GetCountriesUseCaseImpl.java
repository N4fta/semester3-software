package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.GetCountriesUseCase;
import fontys.sem3.school.domain.Country;
import fontys.sem3.school.domain.GetCountriesResponse;
import fontys.sem3.school.repository.entity.CountryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class GetCountriesUseCaseImpl implements GetCountriesUseCase {
    @Override
    public GetCountriesResponse getCountries() {
        List<Country> countries = findAll()
                .stream()
                .map(CountryConverter::convert)
                .toList();

        return GetCountriesResponse.builder()
                .countries(countries)
                .build();
    }

    private List<CountryEntity> findAll() {
        // TODO: replace by country repository method call
        return Collections.emptyList();
    }
}
