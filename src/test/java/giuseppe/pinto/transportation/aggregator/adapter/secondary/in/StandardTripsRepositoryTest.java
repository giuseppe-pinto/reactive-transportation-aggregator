package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.domain.Driver;
import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.DriverRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
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

import static giuseppe.pinto.transportation.aggregator.domain.Driver.BLUE;
import static giuseppe.pinto.transportation.aggregator.domain.Driver.RED;

class StandardTripsRepositoryTest {


    public static final String DEPARTURE = "MIL";
    public static final String ARRIVAL = "NAP";

    @Test
    void allTheDriversAreCalledAndReturnTripsInDifferentMoment() {

        DriverRepository firstDriverRepository = searchRequest -> Mono.just(DriverOutcome
                        .builder().
                        trips(List.of(createTripWith(searchRequest, BLUE)))
                        .build())
                .delayElement(Duration.ofSeconds(1));

        DriverRepository secondDriverRepository = searchRequest -> Mono.just(DriverOutcome
                        .builder()
                        .trips(List.of(createTripWith(searchRequest, RED)))
                        .build())
                    .delayElement(Duration.ofSeconds(3));

        StandardTripsRepository underTest =
                new StandardTripsRepository(searchRequest -> List.of(firstDriverRepository, secondDriverRepository));

        Flux<DriverOutcome> actualTrips = underTest.getDriverOutcomeFrom(createSearchRequest());

        StepVerifier.create(actualTrips)
                .expectNext(
                        DriverOutcome.builder().trips(List.of(createTripWith(createSearchRequest(), BLUE))).build(),
                        DriverOutcome.builder().trips(List.of(createTripWith(createSearchRequest(), RED))).build())
                .expectComplete()
                .verifyThenAssertThat()
                .tookMoreThan(Duration.ofSeconds(3));

    }

    private SearchRequest createSearchRequest() {

        return SearchRequest
                .builder()
                .departure(DEPARTURE)
                .arrival(ARRIVAL)
                .departureDate(LocalDate.now())
                .build();

    }


    private static Trip createTripWith(SearchRequest searchRequest, Driver driver) {
        return Trip.builder()
                .driver(driver)
                .carrier("AIRLINE")
                .carrierNumber("1000")
                .departure(searchRequest.getDeparture())
                .arrival(searchRequest.getArrival())
                .departureDate(LocalDateTime.of(2023, Month.NOVEMBER, 12, 10, 0))
                .arrivalDate(LocalDateTime.of(2023, Month.NOVEMBER, 13, 10, 0))
                .price(new BigDecimal("10.00"))
                .currency(Currency.getInstance(Locale.ITALY))
                .build();
    }
}