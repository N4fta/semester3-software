package fontys.sem3.school.business;

import java.util.Optional;

import fontys.sem3.school.domain.Country;

public interface GetCountryUseCase {
    Optional<Country> getCountry(long countryId);
}
