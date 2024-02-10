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
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static giuseppe.pinto.transportation.aggregator.domain.Supplier.RED;

public class RedBlockingSupplierRepository implements SupplierRepository {

    private static final Logger log = LoggerFactory.getLogger(RedBlockingSupplierRepository.class);

    @Override
    public Mono<SupplierOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest) {
        log.info("Calling the provider: " + RED);


        Trip trip = new Trip(
                oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                oneWaySearchRequest.departureDate().atTime( 10, 0),
                oneWaySearchRequest.departureDate().atTime( 12, 0),
                "2000",
                "FIRST_RED_AIRLINE",
                new BigDecimal("60.00"),
                Currency.getInstance(Locale.ITALY),
                RED);

        Trip secondTrip = new Trip(
                oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                oneWaySearchRequest.departureDate().atTime( 10, 0),
                oneWaySearchRequest.departureDate().atTime( 12, 0),
                "2001",
                "SECOND_RED_AIRLINE",
                new BigDecimal("5.00"),
                Currency.getInstance(Locale.ITALY),
                RED);

        List<Trip> trips = List.of(trip, secondTrip);


        return Mono.just(new SupplierOutcome(trips))
                .delayElement(Duration.ofSeconds(5));
    }
}
