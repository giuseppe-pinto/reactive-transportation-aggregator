package giuseppe.pinto.transportation.aggregator.adapter.secondary.out.reactive;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.ReactiveDriverRepository;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static giuseppe.pinto.transportation.aggregator.domain.Driver.GREEN;

public class GreenReactiveDriverRepository implements ReactiveDriverRepository {
    @Override
    public Flux<DriverOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest) {

        List<Trip> firstChunk = List.of(
                new Trip(oneWaySearchRequest.departure(),
                        oneWaySearchRequest.arrival(),
                        LocalDateTime.of(2023, Month.NOVEMBER, 12, 16, 0),
                        LocalDateTime.of(2023, Month.NOVEMBER, 13, 16, 0),
                        "1000",
                        "BLUE_FIRST_AIRLINE",
                        new BigDecimal("100.00"),
                        Currency.getInstance(Locale.ITALY),
                        GREEN));

        List<Trip> secondChunk = List.of(new Trip(oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                LocalDateTime.of(2023, Month.NOVEMBER, 12, 16, 0),
                LocalDateTime.of(2023, Month.NOVEMBER, 13, 16, 0),
                "2000",
                "GREEN_SECOND_AIRLINE",
                new BigDecimal("135.00"),
                Currency.getInstance(Locale.ITALY),
                GREEN));

        List<Trip> thirdChunk = List.of(new Trip(oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                LocalDateTime.of(2023, Month.NOVEMBER, 12, 16, 0),
                LocalDateTime.of(2023, Month.NOVEMBER, 13, 16, 0),
                "3001",
                "GREEN_THIRD_AIRLINE",
                new BigDecimal("325.00"),
                Currency.getInstance(Locale.ITALY),
                GREEN));

        return Flux.just(new DriverOutcome(firstChunk),
                new DriverOutcome(secondChunk),
                new DriverOutcome(thirdChunk)).delayElements(Duration.ofSeconds(1));

    }

}