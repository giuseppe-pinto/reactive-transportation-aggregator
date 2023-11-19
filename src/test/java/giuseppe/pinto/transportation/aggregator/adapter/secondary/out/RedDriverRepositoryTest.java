package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.domain.Driver;
import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
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

class RedDriverRepositoryTest {


    private static final String DEPARTURE = "LIN";
    private static final String ARRIVAL = "NAP";

    @Test
    void theSecondDriverRespondsIn5Seconds() {

        RedDriverRepository underTest = new RedDriverRepository();

        Mono<List<Trip>> mono = underTest.performRequest(request());

        StepVerifier.create(mono)
                .expectNext(List.of(Trip.builder()
                        .driver(Driver.RED)
                        .carrier("PAOLO_AIRLINE")
                        .carrierNumber("2000")
                        .departure(DEPARTURE)
                        .arrival(ARRIVAL)
                        .departureDate(LocalDateTime.of(2023, Month.NOVEMBER, 12, 10, 0))
                        .arrivalDate(LocalDateTime.of(2023, Month.NOVEMBER, 13, 10, 0))
                        .price(new BigDecimal("60.00"))
                        .currency(Currency.getInstance(Locale.ITALY))
                        .build(),
                        Trip.builder()
                                .driver(Driver.RED)
                                .carrier("MARIO_AIRLINE")
                                .carrierNumber("2050")
                                .departure(DEPARTURE)
                                .arrival(ARRIVAL)
                                .departureDate(LocalDateTime.of(2023, Month.NOVEMBER, 12, 8, 0))
                                .arrivalDate(LocalDateTime.of(2023, Month.NOVEMBER, 13, 8, 0))
                                .price(new BigDecimal("5.00"))
                                .currency(Currency.getInstance(Locale.ITALY))
                                .build()
                        ))
                .expectComplete()
                .verifyThenAssertThat()
                .tookMoreThan(Duration.ofSeconds(5));

    }


    private SearchRequest request() {
        return SearchRequest
                .builder()
                .departure(DEPARTURE)
                .arrival(ARRIVAL)
                .departureDate(LocalDate.now())
                .build();
    }

}