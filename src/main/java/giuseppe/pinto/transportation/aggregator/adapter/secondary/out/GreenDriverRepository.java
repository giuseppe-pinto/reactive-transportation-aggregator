package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.MultiTripReactiveDriverRepository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static giuseppe.pinto.transportation.aggregator.domain.Driver.*;

public class GreenDriverRepository implements MultiTripReactiveDriverRepository {
    @Override
    public Mono<List<Trip>> performRequest(SearchRequest searchRequest) {

        Trip trip = Trip.builder()
                .driver(GREEN)
                .carrier("FRANCO_AIRLINE")
                .carrierNumber("3000")
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
