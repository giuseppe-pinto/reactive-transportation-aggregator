package giuseppe.pinto.transportation.aggregator.adapter.secondary.out.reactive;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.DriverRepository;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static giuseppe.pinto.transportation.aggregator.domain.Driver.BLUE;

public class BlueReactiveDriverRepository implements DriverRepository {

    @Override
    public Flux<DriverOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest) {

        List<Trip> firstChunk = List.of(
                new Trip(oneWaySearchRequest.departure(),
                        oneWaySearchRequest.arrival(),
                        oneWaySearchRequest.departureDate().atTime(16, 0),
                        oneWaySearchRequest.departureDate().atTime(19, 0),
                        "1000",
                        "BLUE_FIRST_AIRLINE",
                        new BigDecimal("35.00"),
                        Currency.getInstance(Locale.ITALY),
                        BLUE),
                new Trip(oneWaySearchRequest.departure(),
                        oneWaySearchRequest.arrival(),
                        oneWaySearchRequest.departureDate().atTime(16, 0),
                        oneWaySearchRequest.departureDate().atTime(19, 0),
                        "1001",
                        "BLUE_FIRST_AIRLINE",
                        new BigDecimal("35.00"),
                        Currency.getInstance(Locale.ITALY),
                        BLUE));

        List<Trip> secondChunk = List.of(new Trip(oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                oneWaySearchRequest.departureDate().atTime(16, 0),
                oneWaySearchRequest.departureDate().atTime(19, 0),
                "2000",
                "BLUE_SECOND_AIRLINE",
                new BigDecimal("35.00"),
                Currency.getInstance(Locale.ITALY),
                BLUE));

        List<Trip> thirdChunk = List.of(new Trip(oneWaySearchRequest.departure(),
                        oneWaySearchRequest.arrival(),
                        oneWaySearchRequest.departureDate().atTime(16, 0),
                        oneWaySearchRequest.departureDate().atTime(19, 0),
                        "3001",
                        "BLUE_THIRD_AIRLINE",
                        new BigDecimal("35.00"),
                        Currency.getInstance(Locale.ITALY),
                        BLUE),
                new Trip(oneWaySearchRequest.departure(),
                        oneWaySearchRequest.arrival(),
                        oneWaySearchRequest.departureDate().atTime(16, 0),
                        oneWaySearchRequest.departureDate().atTime(19, 0),
                        "3002",
                        "BLUE_THIRD_AIRLINE",
                        new BigDecimal("35.00"),
                        Currency.getInstance(Locale.ITALY),
                        BLUE),
                new Trip(oneWaySearchRequest.departure(),
                        oneWaySearchRequest.arrival(),
                        oneWaySearchRequest.departureDate().atTime(16, 0),
                        oneWaySearchRequest.departureDate().atTime(19, 0),
                        "3003",
                        "BLUE_THIRD_AIRLINE",
                        new BigDecimal("35.00"),
                        Currency.getInstance(Locale.ITALY),
                        BLUE));

        return Flux.just(new DriverOutcome(firstChunk),
                new DriverOutcome(secondChunk),
                new DriverOutcome(thirdChunk)).delayElements(Duration.ofSeconds(2));

    }


}
