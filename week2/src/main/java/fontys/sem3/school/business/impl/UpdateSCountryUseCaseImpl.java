package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.UpdateCountryUseCase;
import fontys.sem3.school.domain.UpdateCountryRequest;
import fontys.sem3.school.persistence.CountryRepository;
import fontys.sem3.school.persistence.entity.CountryEntity;
import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateSCountryUseCaseImpl implements UpdateCountryUseCase {
    private final CountryRepository countryRepository;

    @Override
    public void updateCountry(UpdateCountryRequest request) {
        Optional<CountryEntity> countryOptional = Optional.of(countryRepository.findById(request.getId()));
        if (countryOptional.isEmpty()) {
            throw new Error();
        }

        // Most of this code exists to keep consistency with example
        CountryEntity country = CountryEntity.builder()
            .id(request.getId())
            .code(request.getCode())
            .name(request.getName())
            .build();
        
        countryRepository.save(country);
    }

}
