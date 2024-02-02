package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
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

import static giuseppe.pinto.transportation.aggregator.domain.Driver.BLUE;


public class BlueDriverRepository implements DriverRepository {

    private static final Logger log = LoggerFactory.getLogger(BlueDriverRepository.class);

    @Override
    public Mono<DriverOutcome> performRequest(SearchRequest searchRequest) {
        log.info("Calling the provider: " + BLUE);

        List<Trip> trips = List.of(new Trip(
                searchRequest.departure(),
                searchRequest.arrival(),
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
