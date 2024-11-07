package nl.fontys.s3.starter.controller;

import nl.fontys.s3.starter.business.GetTickerPricesUseCase;
import nl.fontys.s3.starter.domain.GetTickerPricesRequest;
import nl.fontys.s3.starter.domain.GetTickerPricesResponse;
import nl.fontys.s3.starter.domain.TickerPrice;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickers/prices/cheapest")
public class TickerPriceCheapestController {
    private final GetTickerPricesUseCase getTickerPricesUseCase;

    public TickerPriceCheapestController(GetTickerPricesUseCase getTickerPricesUseCase) {
        this.getTickerPricesUseCase = getTickerPricesUseCase;
    }

    @GetMapping
    public ResponseEntity<GetTickerPricesResponse> getTickerPrice(@RequestParam("from") String fromCurrency,
                                                                  @RequestParam("to") String toCurrency) {
        GetTickerPricesRequest getTickerPricesRequestDTO = GetTickerPricesRequest.builder()
                .fromCurrency(fromCurrency)
                .toCurrency(toCurrency)
                .build();
        GetTickerPricesResponse responseBody = getTickerPricesUseCase.getTickerPrices(getTickerPricesRequestDTO);

        TickerPrice cheapestTickerPrice = null;
        Double differenceToNextCheapest = null;
        for (TickerPrice price : responseBody.getCurrentPrices()) {
            if (cheapestTickerPrice == null){
                cheapestTickerPrice = price;
            }
            else if (differenceToNextCheapest == null){
                differenceToNextCheapest = cheapestTickerPrice.getPrice() - price.getPrice();
            }
            if (cheapestTickerPrice.getPrice() > price.getPrice()) {
                differenceToNextCheapest = cheapestTickerPrice.getPrice() - price.getPrice();
                cheapestTickerPrice = price;
            }
        }
        responseBody.setCurrentPrices(List.of(cheapestTickerPrice));
        responseBody.setPriceDifference(differenceToNextCheapest);
        return ResponseEntity.ok(responseBody);
    }

}
