package fontys.sem3.school.controller;

import fontys.sem3.school.business.GetCountriesUseCase;
import fontys.sem3.school.domain.GetCountriesResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountriesController {
    private final GetCountriesUseCase getCountriesUseCase;

    @GetMapping
    public ResponseEntity<GetCountriesResponse> getCountries() {
        return ResponseEntity.ok(getCountriesUseCase.getCountries());
    }

}
