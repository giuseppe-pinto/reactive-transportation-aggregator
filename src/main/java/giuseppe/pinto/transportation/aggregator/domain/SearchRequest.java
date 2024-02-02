package giuseppe.pinto.transportation.aggregator.domain;

import java.time.LocalDate;

public record SearchRequest(String departure,
        String arrival,
        LocalDate departureDate,
        LocalDate returnDate) { }
