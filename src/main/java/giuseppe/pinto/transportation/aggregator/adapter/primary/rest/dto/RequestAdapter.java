package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestAdapter {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public OneWaySearchRequest from(OneWaySearchRequestDto oneWaySearchRequestDTO){

        return new OneWaySearchRequest(oneWaySearchRequestDTO.departure(),
                oneWaySearchRequestDTO.arrival(),
                LocalDate.parse(oneWaySearchRequestDTO.departureDate(), formatter)
        );

    }

}
