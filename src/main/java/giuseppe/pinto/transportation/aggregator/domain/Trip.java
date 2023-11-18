package giuseppe.pinto.transportation.aggregator.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Currency;

@Getter
@EqualsAndHashCode
@Builder
@ToString
public class Trip {

    private final String departure;
    private final String arrival;
    private final LocalDateTime departureDate;
    private final LocalDateTime arrivalDate;
    private final String flightNumber;
    private final String airline;
    private final Double price;
    private final Currency currency;
    private final Driver driver;
}
