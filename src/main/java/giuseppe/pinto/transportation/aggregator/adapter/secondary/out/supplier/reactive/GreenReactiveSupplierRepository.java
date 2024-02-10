package giuseppe.pinto.transportation.aggregator.adapter.secondary.out.supplier.reactive;

import giuseppe.pinto.transportation.aggregator.domain.SupplierOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.supplier.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static giuseppe.pinto.transportation.aggregator.domain.Supplier.GREEN;

public class GreenReactiveSupplierRepository implements SupplierRepository {
    private static final Logger log = LoggerFactory.getLogger(GreenReactiveSupplierRepository.class);
    @Override
    public Flux<SupplierOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest) {

        log.info("Calling the supplier: " + GREEN);

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

        return Flux.just(new SupplierOutcome(firstChunk),
                new SupplierOutcome(secondChunk),
                new SupplierOutcome(thirdChunk)).delayElements(Duration.ofMillis(3000));

    }

}