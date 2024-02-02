package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.blocking.RedBlockingDriverRepository;
import giuseppe.pinto.transportation.aggregator.domain.Driver;
import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

class RedBlockingDriverRepositoryTest {


    private static final String DEPARTURE = "LIN";
    private static final String ARRIVAL = "NAP";

    @Test
    void theSecondDriverRespondsIn5Seconds() {

        RedBlockingDriverRepository underTest = new RedBlockingDriverRepository();

        Mono<DriverOutcome> mono = underTest.performRequest(request());


        Trip firstTrip = new Trip(
                DEPARTURE,
                ARRIVAL,
                LocalDateTime.of(2023, Month.NOVEMBER, 12, 10, 0),
                LocalDateTime.of(2023, Month.NOVEMBER, 13, 10, 0),
                "2000",
                "PAOLO_AIRLINE",
                new BigDecimal("60.00"),
                Currency.getInstance(Locale.ITALY),
                Driver.RED
        );

        Trip secondTrip = new Trip(
                DEPARTURE,
                ARRIVAL,
                LocalDateTime.of(2023, Month.NOVEMBER, 12, 8, 0),
                LocalDateTime.of(2023, Month.NOVEMBER, 13, 8, 0),
                "2050",
                "MARIO_AIRLINE",
                new BigDecimal("5.00"),
                Currency.getInstance(Locale.ITALY),
                Driver.RED
        );


        StepVerifier.create(mono)
                .expectNext(
                        new DriverOutcome(
                        List.of(firstTrip,secondTrip)))
                .expectComplete()
                .verifyThenAssertThat()
                .tookMoreThan(Duration.ofSeconds(5));

    }


    private OneWaySearchRequest request() {
        return new OneWaySearchRequest(
                DEPARTURE,
                ARRIVAL,
                LocalDate.now()
        );
    }

}