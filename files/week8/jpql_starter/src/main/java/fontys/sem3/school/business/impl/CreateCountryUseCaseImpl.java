package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.CreateCountryUseCase;
import fontys.sem3.school.business.exception.InvalidCountryException;
import fontys.sem3.school.domain.CreateCountryRequest;
import fontys.sem3.school.domain.CreateCountryResponse;
import fontys.sem3.school.repository.entity.CountryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCountryUseCaseImpl implements CreateCountryUseCase {
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
        // TODO: replace by countryRepository correct method call and return
        return CountryEntity.builder().build();
    }

    private boolean existsByCode(String countryCode) {
        // TODO: replace by countryRepository correct method call and return
        return false;
    }
}
