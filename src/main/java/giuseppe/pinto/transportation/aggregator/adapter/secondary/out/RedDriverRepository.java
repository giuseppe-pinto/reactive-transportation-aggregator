package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static giuseppe.pinto.transportation.aggregator.domain.Driver.*;

@Slf4j
public class RedDriverRepository implements DriverRepository {
    @Override
    public Mono<DriverOutcome> performRequest(SearchRequest searchRequest) {
        log.info("Calling the provider: " + RED);


        Trip trip = Trip.builder()
                .driver(RED)
                .carrier("PAOLO_AIRLINE")
                .carrierNumber("2000")
                .departure(searchRequest.getDeparture())
                .arrival(searchRequest.getArrival())
                .departureDate(LocalDateTime.of(2023, Month.NOVEMBER, 12, 10, 0))
                .arrivalDate(LocalDateTime.of(2023, Month.NOVEMBER, 13, 10, 0))
                .price(new BigDecimal("60.00"))
                .currency(Currency.getInstance(Locale.ITALY))
                .build();

        Trip secondTrip = Trip.builder()
                .driver(RED)
                .carrier("MARIO_AIRLINE")
                .carrierNumber("2050")
                .departure(searchRequest.getDeparture())
                .arrival(searchRequest.getArrival())
                .departureDate(LocalDateTime.of(2023, Month.NOVEMBER, 12, 8, 0))
                .arrivalDate(LocalDateTime.of(2023, Month.NOVEMBER, 13, 8, 0))
                .price(new BigDecimal("5.00"))
                .currency(Currency.getInstance(Locale.ITALY))
                .build();

        List<Trip> trips = List.of(trip, secondTrip);


        return Mono.just(DriverOutcome.builder().trips(trips).build())
                .delayElement(Duration.ofSeconds(5));
    }
}
