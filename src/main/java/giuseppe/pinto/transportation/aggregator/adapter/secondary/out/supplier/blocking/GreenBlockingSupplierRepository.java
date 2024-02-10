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

import static giuseppe.pinto.transportation.aggregator.domain.Supplier.GREEN;

public class GreenBlockingSupplierRepository implements SupplierRepository {

    private static final Logger log = LoggerFactory.getLogger(GreenBlockingSupplierRepository.class);

    @Override
    public Mono<SupplierOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest) {
        log.info("Calling the supplier: " + GREEN);


        List<Trip> trips = List.of(
                new Trip(
                        oneWaySearchRequest.departure(),
                        oneWaySearchRequest.arrival(),
                        LocalDateTime.of(2023, Month.NOVEMBER, 12, 16, 0),
                        LocalDateTime.of(2023, Month.NOVEMBER, 13, 16, 0),
                        "3000",
                        "FIRST_GREEN_AIRLINE",
                        new BigDecimal("35.00"),
                        Currency.getInstance(Locale.ITALY),
                        GREEN
                ));

        return Mono.just(new SupplierOutcome(trips))
                .delayElement(Duration.ofSeconds(7));

    }
}
