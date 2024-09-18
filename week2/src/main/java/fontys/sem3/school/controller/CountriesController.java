package fontys.sem3.school.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fontys.sem3.school.business.CreateCountryUseCase;
import fontys.sem3.school.business.DeleteCountryUseCase;
import fontys.sem3.school.business.GetCountriesUseCase;
import fontys.sem3.school.business.GetCountryUseCase;
import fontys.sem3.school.business.UpdateCountryUseCase;
import fontys.sem3.school.domain.Country;
import fontys.sem3.school.domain.CreateCountryRequest;
import fontys.sem3.school.domain.CreateCountryResponse;
import fontys.sem3.school.domain.GetCountriesResponse;
import fontys.sem3.school.domain.UpdateCountryRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountriesController {
    private final GetCountriesUseCase getCountriesUseCase;
    private final GetCountryUseCase getCountryUseCase;
    private final DeleteCountryUseCase deleteCountryUseCase;
    private final UpdateCountryUseCase updateCountryUseCase;
    private final CreateCountryUseCase createCountryUseCase;

    @GetMapping
    public ResponseEntity<GetCountriesResponse> getCountries() {
        return ResponseEntity.ok(getCountriesUseCase.getCountries());
    }

    @GetMapping("{id}")
    public ResponseEntity<Country> getCountry(@PathVariable(value = "id") final long id) {
        final Optional<Country> countryOptional = getCountryUseCase.getCountry(id);
        if (countryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(countryOptional.get());
    }

    @DeleteMapping("{countryId}")
    public ResponseEntity<Void> deleteCountry(@PathVariable int countryId) {
        deleteCountryUseCase.deleteCountry(countryId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<CreateCountryResponse> createCountry(@RequestBody @Valid CreateCountryRequest request) {
        CreateCountryResponse response = createCountryUseCase.createCountry(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(("{id}"))
    public ResponseEntity<Void> updateCountry(@PathVariable("id") long id, @RequestBody @Valid UpdateCountryRequest request) {
        request.setId(id);
        updateCountryUseCase.updateCountry(request);
        return ResponseEntity.noContent().build();
    }
}
