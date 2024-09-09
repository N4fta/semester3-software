package nl.fontys.s3.starter.business.impl;

import nl.fontys.s3.starter.business.GetTickerPricesUseCase;
import nl.fontys.s3.starter.domain.GetTickerPricesRequest;
import nl.fontys.s3.starter.domain.GetTickerPricesResponse;
import nl.fontys.s3.starter.domain.TickerPrice;
import nl.fontys.s3.starter.persistence.TickerPriceRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetTickerPricesUseCaseImpl implements GetTickerPricesUseCase {
    private final List<TickerPriceRepository> tickerPriceRepositories;

    public GetTickerPricesUseCaseImpl(List<TickerPriceRepository> tickerPriceRepositories) {
        this.tickerPriceRepositories = tickerPriceRepositories;
    }

    @Override
    public GetTickerPricesResponse getTickerPrices(GetTickerPricesRequest request) {
        List<TickerPrice> prices = new ArrayList<>();

        for (TickerPriceRepository tickerPriceRepository : tickerPriceRepositories) {
            prices.add(tickerPriceRepository.getCurrentPrice(request.getFromCurrency(), request.getToCurrency()));
        }

        return GetTickerPricesResponse.builder()
                .fromCurrency(request.getFromCurrency())
                .toCurrency(request.getToCurrency())
                .currentPrices(prices)
                .build();
    }
}
