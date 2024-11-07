package fontys.sem3.school.controller;

import fontys.sem3.school.business.CreateCountryUseCase;
import fontys.sem3.school.business.GetCountriesUseCase;
import fontys.sem3.school.domain.CreateCountryRequest;
import fontys.sem3.school.domain.CreateCountryResponse;
import fontys.sem3.school.domain.GetCountriesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountriesController {
    private final GetCountriesUseCase getCountriesUseCase;
    private final CreateCountryUseCase createCountryUseCase;

    @GetMapping
    public ResponseEntity<GetCountriesResponse> getCountries() {
        return ResponseEntity.ok(getCountriesUseCase.getCountries());
    }

    @PostMapping
    public ResponseEntity<CreateCountryResponse> createCountry(
            @RequestBody @Valid CreateCountryRequest createCountryRequest) {
        CreateCountryResponse response = createCountryUseCase.createCountry(createCountryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
