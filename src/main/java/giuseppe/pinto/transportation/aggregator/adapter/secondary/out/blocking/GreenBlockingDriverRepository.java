package giuseppe.pinto.transportation.aggregator.adapter.secondary.out.blocking;

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

import static giuseppe.pinto.transportation.aggregator.domain.Driver.GREEN;

public class GreenBlockingDriverRepository implements DriverRepository {

    private static final Logger log = LoggerFactory.getLogger(GreenBlockingDriverRepository.class);

    @Override
    public Mono<DriverOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest) {
        log.info("Calling the provider: " + GREEN);


        List<Trip> trips = List.of(
                new Trip(
                        oneWaySearchRequest.departure(),
                        oneWaySearchRequest.arrival(),
                        LocalDateTime.of(2023, Month.NOVEMBER, 12, 16, 0),
                        LocalDateTime.of(2023, Month.NOVEMBER, 13, 16, 0),
                        "3000",
                        "FRANCO_AIRLINE",
                        new BigDecimal("35.00"),
                        Currency.getInstance(Locale.ITALY),
                        GREEN
                ));

        return Mono.just(new DriverOutcome(trips))
                .delayElement(Duration.ofSeconds(7));

    }
}
