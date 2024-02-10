package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.domain.Supplier;
import giuseppe.pinto.transportation.aggregator.domain.SupplierOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.SuppliersConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.out.supplier.SupplierRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static giuseppe.pinto.transportation.aggregator.domain.Supplier.BLUE;
import static giuseppe.pinto.transportation.aggregator.domain.Supplier.RED;

class StandardSupplierOutcomeServiceTest {

    private static final String DEPARTURE = "MIL";
    private static final String ARRIVAL = "NAP";
    private static final LocalDate DEPARTURE_DATE = LocalDate.of(2023, Month.NOVEMBER, 12);

    @Test
    void allThesAreCalledAndReturnTripsInDifferentMoment() {

        StandardSuppliersOutcomeService underTest =
                new StandardSuppliersOutcomeService(new FakeSuppliersConfigurationRepository());

        Flux<SupplierOutcome> actualTrips = underTest.from(createSearchRequest());

        StepVerifier.create(actualTrips)
                .expectNext(new SupplierOutcome(List.of(createTripWith(createSearchRequest(), BLUE))))
                .expectNext(new SupplierOutcome(List.of(createTripWith(createSearchRequest(), RED))))
                .expectComplete()
                .verifyThenAssertThat()
                .tookMoreThan(Duration.ofSeconds(3));

    }

    private static class FakeSuppliersConfigurationRepository implements SuppliersConfigurationRepository {


        @Override
        public Flux<SupplierRepository> getSuppliersFor(OneWaySearchRequest oneWaySearchRequest) {

            SupplierRepository firstSupplierRepository = searchRequest -> Flux.just(
                    new SupplierOutcome(List.of(createTripWith(searchRequest, BLUE))))
                    .delayElements(Duration.ofSeconds(1));

            SupplierRepository secondSupplierRepository = searchRequest -> Flux.just(
                    new SupplierOutcome((List.of(createTripWith(searchRequest, RED)))))
                    .delayElements(Duration.ofSeconds(3));

            return Flux.just(firstSupplierRepository, secondSupplierRepository);
        }


    }

    private OneWaySearchRequest createSearchRequest() {

        return new OneWaySearchRequest(DEPARTURE,
                ARRIVAL,
                DEPARTURE_DATE
        );

    }


    private static Trip createTripWith(OneWaySearchRequest oneWaySearchRequest, Supplier supplier) {
        return new Trip(
                oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                DEPARTURE_DATE.atTime( 10, 0),
                DEPARTURE_DATE.atTime( 12, 0),
                "1000",
                "AIRLINE",
                new BigDecimal("10.00"),
                Currency.getInstance(Locale.ITALY),
                supplier);
    }
}