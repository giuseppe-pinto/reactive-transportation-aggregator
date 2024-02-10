package giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.reactive;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.driver.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static giuseppe.pinto.transportation.aggregator.domain.Driver.GREEN;

public class GreenReactiveDriverRepository implements DriverRepository {
    private static final Logger log = LoggerFactory.getLogger(GreenReactiveDriverRepository.class);
    @Override
    public Flux<DriverOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest) {

        log.info("Calling the provider: " + GREEN);

        List<Trip> firstChunk = List.of(
                new Trip(oneWaySearchRequest.departure(),
                        oneWaySearchRequest.arrival(),
                        oneWaySearchRequest.departureDate().atTime(16, 0),
                        oneWaySearchRequest.departureDate().atTime(19, 0),
                        "1000",
                        "GREEN_FIRST_AIRLINE",
                        new BigDecimal("100.00"),
                        Currency.getInstance(Locale.ITALY),
                        GREEN));

        List<Trip> secondChunk = List.of(new Trip(oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                oneWaySearchRequest.departureDate().atTime(16, 0),
                oneWaySearchRequest.departureDate().atTime(19, 0),
                "2000",
                "GREEN_SECOND_AIRLINE",
                new BigDecimal("135.00"),
                Currency.getInstance(Locale.ITALY),
                GREEN));

        List<Trip> thirdChunk = List.of(new Trip(oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                oneWaySearchRequest.departureDate().atTime(16, 0),
                oneWaySearchRequest.departureDate().atTime(19, 0),
                "3000",
                "GREEN_THIRD_AIRLINE",
                new BigDecimal("325.00"),
                Currency.getInstance(Locale.ITALY),
                GREEN));

        return Flux.just(new DriverOutcome(firstChunk),
                new DriverOutcome(secondChunk),
                new DriverOutcome(thirdChunk)).delayElements(Duration.ofMillis(3000));

    }

}