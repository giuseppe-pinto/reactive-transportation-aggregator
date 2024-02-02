package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static giuseppe.pinto.transportation.aggregator.domain.Driver.RED;

public class RedDriverRepository implements DriverRepository {

    private static final Logger log = LoggerFactory.getLogger(RedDriverRepository.class);

    @Override
    public Mono<DriverOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest) {
        log.info("Calling the provider: " + RED);


        Trip trip = new Trip(
                oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                LocalDateTime.of(2023, Month.NOVEMBER, 12, 10, 0),
                LocalDateTime.of(2023, Month.NOVEMBER, 13, 10, 0),
                "2000",
                "PAOLO_AIRLINE",
                new BigDecimal("60.00"),
                Currency.getInstance(Locale.ITALY),
                RED);

        Trip secondTrip = new Trip(
                oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                LocalDateTime.of(2023, Month.NOVEMBER, 12, 8, 0),
                LocalDateTime.of(2023, Month.NOVEMBER, 13, 8, 0),
                "2050",
                "MARIO_AIRLINE",
                new BigDecimal("5.00"),
                Currency.getInstance(Locale.ITALY),
                RED);

        List<Trip> trips = List.of(trip, secondTrip);


        return Mono.just(new DriverOutcome(trips))
                .delayElement(Duration.ofSeconds(5));
    }
}
