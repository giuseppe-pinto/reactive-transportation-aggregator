package giuseppe.pinto.transportation.aggregator.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public record Trip(String departure,
                   String arrival,
                   LocalDateTime departureDate,
                   LocalDateTime arrivalDate,
                   String carrierNumber,
                   String carrier,
                   BigDecimal price,
                   Currency currency,
                   Driver driver) {
}
