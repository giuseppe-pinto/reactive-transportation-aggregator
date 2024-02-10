package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.domain.Driver;
import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.DriverConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.out.driver.DriverRepository;
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

import static giuseppe.pinto.transportation.aggregator.domain.Driver.BLUE;
import static giuseppe.pinto.transportation.aggregator.domain.Driver.RED;

class StandardDriverOutcomeServiceTest {

    private static final String DEPARTURE = "MIL";
    private static final String ARRIVAL = "NAP";
    private static final LocalDate DEPARTURE_DATE = LocalDate.of(2023, Month.NOVEMBER, 12);

    @Test
    void allTheDriversAreCalledAndReturnTripsInDifferentMoment() {

        StandardDriverOutcomeService underTest =
                new StandardDriverOutcomeService(new FakeDriverConfigurationRepository());

        Flux<DriverOutcome> actualTrips = underTest.from(createSearchRequest());

        StepVerifier.create(actualTrips)
                .expectNext(new DriverOutcome(List.of(createTripWith(createSearchRequest(), BLUE))))
                .expectNext(new DriverOutcome(List.of(createTripWith(createSearchRequest(), RED))))
                .expectComplete()
                .verifyThenAssertThat()
                .tookMoreThan(Duration.ofSeconds(3));

    }

    private static class FakeDriverConfigurationRepository implements DriverConfigurationRepository{


        @Override
        public Flux<DriverRepository> getDriversFor(OneWaySearchRequest oneWaySearchRequest) {

            DriverRepository firstDriverRepository = searchRequest -> Flux.just(
                    new DriverOutcome(List.of(createTripWith(searchRequest, BLUE))))
                    .delayElements(Duration.ofSeconds(1));

            DriverRepository secondDriverRepository = searchRequest -> Flux.just(
                    new DriverOutcome((List.of(createTripWith(searchRequest, RED)))))
                    .delayElements(Duration.ofSeconds(3));

            return Flux.just(firstDriverRepository, secondDriverRepository);
        }


    }

    private OneWaySearchRequest createSearchRequest() {

        return new OneWaySearchRequest(DEPARTURE,
                ARRIVAL,
                DEPARTURE_DATE
        );

    }


    private static Trip createTripWith(OneWaySearchRequest oneWaySearchRequest, Driver driver) {
        return new Trip(
                oneWaySearchRequest.departure(),
                oneWaySearchRequest.arrival(),
                DEPARTURE_DATE.atTime( 10, 0),
                DEPARTURE_DATE.atTime( 12, 0),
                "1000",
                "AIRLINE",
                new BigDecimal("10.00"),
                Currency.getInstance(Locale.ITALY),
                driver);
    }
}