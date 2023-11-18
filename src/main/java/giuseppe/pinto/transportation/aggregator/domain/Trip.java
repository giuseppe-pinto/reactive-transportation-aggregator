package giuseppe.pinto.transportation.aggregator.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
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
    private final String carrierNumber;
    private final String carrier;
    private final BigDecimal price;
    private final Currency currency;
    private final Driver driver;
}
