package giuseppe.pinto.transportation.aggregator.adapter.secondary.out.mono;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.BlockingDriverRepository;
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

import static giuseppe.pinto.transportation.aggregator.domain.Driver.BLUE;


public class BlueBlockingDriverRepository implements BlockingDriverRepository {

    private static final Logger log = LoggerFactory.getLogger(BlueBlockingDriverRepository.class);

    @Override
    public Mono<DriverOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest) {
        log.info("Calling the provider: " + BLUE);

        List<Trip> trips = List.of(new Trip(
                oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                LocalDateTime.of(2023, Month.NOVEMBER, 12, 10, 0),
                LocalDateTime.of(2023, Month.NOVEMBER, 13, 10, 0),
                "1000",
                "GIUSEPPE_AIRLINE",
                new BigDecimal("10.00"),
                Currency.getInstance(Locale.ITALY),
                BLUE));


        return Mono.just(new DriverOutcome(trips))
                .delayElement(Duration.ofSeconds(3));
    }
}
