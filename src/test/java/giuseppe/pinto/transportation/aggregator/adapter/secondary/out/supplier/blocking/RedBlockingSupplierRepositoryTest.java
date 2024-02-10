package giuseppe.pinto.transportation.aggregator.adapter.secondary.out.supplier.blocking;

import giuseppe.pinto.transportation.aggregator.domain.Supplier;
import giuseppe.pinto.transportation.aggregator.domain.SupplierOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

class RedBlockingSupplierRepositoryTest {


    private static final String DEPARTURE = "LIN";
    private static final String ARRIVAL = "NAP";
    private static final LocalDate DEPARTURE_DATE =
            LocalDate.of(2023, Month.NOVEMBER, 12);

    @Test
    void driverRespondsIn5Seconds() {

        RedBlockingSupplierRepository underTest = new RedBlockingSupplierRepository();

        Mono<SupplierOutcome> performed = underTest.performRequest(request());


        SupplierOutcome providerResult = new SupplierOutcome(List.of(new Trip(
                DEPARTURE,
                ARRIVAL,
                DEPARTURE_DATE.atTime(10, 0),
                DEPARTURE_DATE.atTime(12, 0),
                "2000",
                "FIRST_RED_AIRLINE",
                new BigDecimal("60.00"),
                Currency.getInstance(Locale.ITALY),
                Supplier.RED
        ), new Trip(
                DEPARTURE,
                ARRIVAL,
                DEPARTURE_DATE.atTime(10, 0),
                DEPARTURE_DATE.atTime(12, 0),
                "2001",
                "SECOND_RED_AIRLINE",
                new BigDecimal("5.00"),
                Currency.getInstance(Locale.ITALY),
                Supplier.RED
        )));


        StepVerifier.create(performed)
                .expectNext(providerResult)
                .expectComplete()
                .verifyThenAssertThat()
                .tookLessThan(Duration.ofSeconds(6));

    }


    private OneWaySearchRequest request() {
        return new OneWaySearchRequest(
                DEPARTURE,
                ARRIVAL,
                DEPARTURE_DATE
        );
    }

}