package giuseppe.pinto.transportation.aggregator.domain;

import java.time.LocalDate;

public record OneWaySearchRequest(String departure,
                                  String arrival,
                                  LocalDate departureDate
) { }
