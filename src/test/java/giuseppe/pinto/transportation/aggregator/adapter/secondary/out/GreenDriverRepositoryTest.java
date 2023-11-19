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

class GreenDriverRepositoryTest {

    private static final String DEPARTURE = "LIN";
    private static final String ARRIVAL = "NAP";

    @Test
    void theThirdDriverRespondsIn7Seconds() {

        GreenDriverRepository underTest = new GreenDriverRepository();

        Mono<List<Trip>> mono = underTest.performRequest(request());

        StepVerifier.create(mono)
                .expectNext(List.of(Trip.builder()
                        .driver(Driver.GREEN)
                        .carrier("FRANCO_AIRLINE")
                        .carrierNumber("3000")
                        .departure(DEPARTURE)
                        .arrival(ARRIVAL)
                        .departureDate(LocalDateTime.of(2023, Month.NOVEMBER, 12, 16, 0))
                        .arrivalDate(LocalDateTime.of(2023, Month.NOVEMBER, 13, 16, 0))
                        .price(new BigDecimal("35.00"))
                        .currency(Currency.getInstance(Locale.ITALY))
                        .build()))
                .expectComplete()
                .verifyThenAssertThat()
                .tookMoreThan(Duration.ofSeconds(7));


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