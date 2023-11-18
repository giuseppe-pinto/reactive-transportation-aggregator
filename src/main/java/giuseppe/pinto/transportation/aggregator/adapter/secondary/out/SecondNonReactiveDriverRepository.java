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

public class SecondNonReactiveDriverRepository implements NonReactiveDriverRepository {
    @Override
    public Mono<List<Trip>> performRequest(SearchRequest searchRequest) {


        Trip trip = Trip.builder()
                .driver(Driver.SECOND)
                .airline("PAOLO_AIRLINE")
                .flightNumber("2000")
                .departure(searchRequest.getDeparture())
                .arrival(searchRequest.getArrival())
                .departureDate(LocalDateTime.of(2023, Month.NOVEMBER, 12, 10, 0))
                .arrivalDate(LocalDateTime.of(2023, Month.NOVEMBER, 13, 10, 0))
                .price(new BigDecimal("60.00"))
                .currency(Currency.getInstance(Locale.ITALY))
                .build();

        Trip secondTrip = Trip.builder()
                .driver(Driver.SECOND)
                .airline("MARIO_AIRLINE")
                .flightNumber("2050")
                .departure(searchRequest.getDeparture())
                .arrival(searchRequest.getArrival())
                .departureDate(LocalDateTime.of(2023, Month.NOVEMBER, 12, 8, 0))
                .arrivalDate(LocalDateTime.of(2023, Month.NOVEMBER, 13, 8, 0))
                .price(new BigDecimal("5.00"))
                .currency(Currency.getInstance(Locale.ITALY))
                .build();

        return Mono.just(List.of(trip, secondTrip)).delayElement(Duration.ofSeconds(5));

    }
}
