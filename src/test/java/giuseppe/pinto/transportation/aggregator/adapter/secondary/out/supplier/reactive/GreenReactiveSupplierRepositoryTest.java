package giuseppe.pinto.transportation.aggregator.adapter.secondary.out.supplier.reactive;

import giuseppe.pinto.transportation.aggregator.domain.Supplier;
import giuseppe.pinto.transportation.aggregator.domain.SupplierOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

class GreenReactiveSupplierRepositoryTest {

    private static final String DEPARTURE = "LIN";
    private static final String ARRIVAL = "NAP";
    private static final LocalDate DEPARTURE_DATE = LocalDate.of(2024, 3, 5);


    @Test
    void supplierRespondsIn3Seconds() {

        GreenReactiveSupplierRepository underTest = new GreenReactiveSupplierRepository();

        Flux<SupplierOutcome> performed = underTest.performRequest(request());

        SupplierOutcome firstSupplierResult = new SupplierOutcome(
                List.of(new Trip(
                        DEPARTURE,
                        ARRIVAL,
                        DEPARTURE_DATE.atTime(16, 0),
                        DEPARTURE_DATE.atTime(19, 0),
                        "1000",
                        "GREEN_FIRST_AIRLINE",
                        new BigDecimal("100.00"),
                        Currency.getInstance(Locale.ITALY),
                        Supplier.GREEN
                )));


        SupplierOutcome secondSupplierResult = new SupplierOutcome(
                List.of(new Trip(
                        DEPARTURE,
                        ARRIVAL,
                        DEPARTURE_DATE.atTime(16, 0),
                        DEPARTURE_DATE.atTime(19, 0),
                        "2000",
                        "GREEN_SECOND_AIRLINE",
                        new BigDecimal("135.00"),
                        Currency.getInstance(Locale.ITALY),
                        Supplier.GREEN
                )));
        SupplierOutcome thirdSupplierResult = new SupplierOutcome(List.of(new Trip(
                DEPARTURE,
                ARRIVAL,
                DEPARTURE_DATE.atTime(16, 0),
                DEPARTURE_DATE.atTime(19, 0),
                "3000",
                "GREEN_THIRD_AIRLINE",
                new BigDecimal("325.00"),
                Currency.getInstance(Locale.ITALY),
                Supplier.GREEN
        )));

        StepVerifier.create(performed)
                .expectNext(firstSupplierResult)
                .expectNext(secondSupplierResult)
                .expectNext(thirdSupplierResult)
                .expectComplete()
                .verifyThenAssertThat()
                .tookLessThan(Duration.ofMillis(9500));

    }

    private OneWaySearchRequest request() {
        return new OneWaySearchRequest(
                DEPARTURE,
                ARRIVAL,
                DEPARTURE_DATE
        );
    }

}