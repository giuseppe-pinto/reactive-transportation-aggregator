package giuseppe.pinto.transportation.aggregator.adapter.secondary.out.supplier.blocking;

import giuseppe.pinto.transportation.aggregator.domain.SupplierOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.supplier.SupplierRepository;
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

import static giuseppe.pinto.transportation.aggregator.domain.Supplier.BLUE;


public class BlueBlockingSupplierRepository implements SupplierRepository {

    private static final Logger log = LoggerFactory.getLogger(BlueBlockingSupplierRepository.class);

    @Override
    public Mono<SupplierOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest) {
        log.info("Calling the provider: " + BLUE);

        List<Trip> trips = List.of(new Trip(
                oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                LocalDateTime.of(2023, Month.NOVEMBER, 12, 10, 0),
                LocalDateTime.of(2023, Month.NOVEMBER, 13, 10, 0),
                "1000",
                "FIRST_BLUE_AIRLINE",
                new BigDecimal("10.00"),
                Currency.getInstance(Locale.ITALY),
                BLUE));


        return Mono.just(new SupplierOutcome(trips))
                .delayElement(Duration.ofSeconds(3));
    }
}
