package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.domain.Driver;
import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.NonReactiveDriverRepository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static giuseppe.pinto.transportation.aggregator.domain.Driver.*;

public class ThirdNonReactiveDriverRepository implements NonReactiveDriverRepository {
    @Override
    public Mono<List<Trip>> performRequest(SearchRequest searchRequest) {

        Trip trip = Trip.builder()
                .driver(THIRD)
                .airline("FRANCO_AIRLINE")
                .flightNumber("3000")
                .departure(searchRequest.getDeparture())
                .arrival(searchRequest.getArrival())
                .departureDate(LocalDateTime.of(2023, Month.NOVEMBER, 12, 16, 0))
                .arrivalDate(LocalDateTime.of(2023, Month.NOVEMBER, 13, 16, 0))
                .price(new BigDecimal("35.00"))
                .currency(Currency.getInstance(Locale.ITALY))
                .build();

        return Mono.just(List.of(trip)).delayElement(Duration.ofSeconds(7));

    }
}
