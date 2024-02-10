package giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.reactive;

import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.reactive.GreenReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.domain.Driver;
import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
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

class GreenReactiveDriverRepositoryTest {

    private static final String DEPARTURE = "LIN";
    private static final String ARRIVAL = "NAP";
    private static final LocalDate DEPARTURE_DATE = LocalDate.of(2024, 3, 5);


    @Test
    void driverRespondsIn3Seconds() {

        GreenReactiveDriverRepository underTest = new GreenReactiveDriverRepository();

        Flux<DriverOutcome> performed = underTest.performRequest(request());

        DriverOutcome firstDriverResult = new DriverOutcome(
                List.of(new Trip(
                        DEPARTURE,
                        ARRIVAL,
                        DEPARTURE_DATE.atTime(16, 0),
                        DEPARTURE_DATE.atTime(19, 0),
                        "1000",
                        "GREEN_FIRST_AIRLINE",
                        new BigDecimal("100.00"),
                        Currency.getInstance(Locale.ITALY),
                        Driver.GREEN
                )));


        DriverOutcome secondDriverResult = new DriverOutcome(
                List.of(new Trip(
                        DEPARTURE,
                        ARRIVAL,
                        DEPARTURE_DATE.atTime(16, 0),
                        DEPARTURE_DATE.atTime(19, 0),
                        "2000",
                        "GREEN_SECOND_AIRLINE",
                        new BigDecimal("135.00"),
                        Currency.getInstance(Locale.ITALY),
                        Driver.GREEN
                )));
        DriverOutcome thirdDriverResult = new DriverOutcome(List.of(new Trip(
                DEPARTURE,
                ARRIVAL,
                DEPARTURE_DATE.atTime(16, 0),
                DEPARTURE_DATE.atTime(19, 0),
                "3000",
                "GREEN_THIRD_AIRLINE",
                new BigDecimal("325.00"),
                Currency.getInstance(Locale.ITALY),
                Driver.GREEN
        )));

        StepVerifier.create(performed)
                .expectNext(firstDriverResult)
                .expectNext(secondDriverResult)
                .expectNext(thirdDriverResult)
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