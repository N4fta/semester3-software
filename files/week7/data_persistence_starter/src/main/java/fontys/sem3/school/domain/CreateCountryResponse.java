package fontys.sem3.school.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCountryResponse {
    private Long countryId;
}
