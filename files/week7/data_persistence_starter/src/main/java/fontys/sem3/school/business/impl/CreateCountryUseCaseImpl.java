package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.CreateCountryUseCase;
import fontys.sem3.school.business.exception.InvalidCountryException;
import fontys.sem3.school.domain.CreateCountryRequest;
import fontys.sem3.school.domain.CreateCountryResponse;
import fontys.sem3.school.repository.CountryRepository;
import fontys.sem3.school.repository.entity.CountryEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCountryUseCaseImpl implements CreateCountryUseCase {

    private CountryRepository countryRepository;

    @Autowired
    public CreateCountryUseCaseImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CreateCountryResponse createCountry(CreateCountryRequest request) {

        if (existsByCode(request.getCode())) {
            throw new InvalidCountryException("CODE_DUPLICATED");
        }

        CountryEntity newCountry = CountryEntity.builder()
                .name(request.getName())
                .code(request.getCode())
                .build();

        CountryEntity savedCountry = save(newCountry);

        return CreateCountryResponse.builder()
                .countryId(savedCountry.getId())
                .build();
    }

    private CountryEntity save(CountryEntity country) {
        countryRepository.save(country);
        return CountryEntity.builder().build();
    }

    private boolean existsByCode(String countryCode) {
        return countryRepository.existsByCode(countryCode);
    }
}
