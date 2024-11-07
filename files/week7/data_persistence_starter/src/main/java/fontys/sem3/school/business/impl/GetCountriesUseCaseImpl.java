package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.GetCountriesUseCase;
import fontys.sem3.school.domain.Country;
import fontys.sem3.school.domain.GetCountriesResponse;
import fontys.sem3.school.repository.CountryRepository;
import fontys.sem3.school.repository.entity.CountryEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCountriesUseCaseImpl implements GetCountriesUseCase {

    private CountryRepository countryRepository;

    @Autowired
    public GetCountriesUseCaseImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

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
        return countryRepository.findAll();
    }
}
