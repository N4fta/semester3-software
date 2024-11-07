package fontys.sem3.school.business.impl;

import fontys.sem3.school.domain.Country;
import fontys.sem3.school.repository.entity.CountryEntity;

final class CountryConverter {
    private CountryConverter() {
    }

    public static Country convert(CountryEntity countryEntity) {
        return Country.builder()
                .id(countryEntity.getId())
                .code(countryEntity.getCode())
                .name(countryEntity.getName())
                .build();
    }
}
