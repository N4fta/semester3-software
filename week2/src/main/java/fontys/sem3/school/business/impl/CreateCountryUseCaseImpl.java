package fontys.sem3.school.business.impl;

import org.springframework.stereotype.Service;

import fontys.sem3.school.business.CreateCountryUseCase;
import fontys.sem3.school.domain.CreateCountryRequest;
import fontys.sem3.school.domain.CreateCountryResponse;
import fontys.sem3.school.persistence.CountryRepository;
import fontys.sem3.school.persistence.entity.CountryEntity;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateCountryUseCaseImpl implements CreateCountryUseCase {
    private final CountryRepository countryRepository;

    @Override
    public CreateCountryResponse createCountry(CreateCountryRequest request) {
        if (countryRepository.existsByCode(request.getCode())){
            throw new Error();
        }

        CountryEntity countryEntity = CountryEntity.builder()
            .code(request.getCode())
            .name(request.getName())
            .build();

        CountryEntity savedCountry = countryRepository.save(countryEntity);

        return CreateCountryResponse.builder()
            .countrytId(savedCountry.getId())
            .build();
    }

    
}
