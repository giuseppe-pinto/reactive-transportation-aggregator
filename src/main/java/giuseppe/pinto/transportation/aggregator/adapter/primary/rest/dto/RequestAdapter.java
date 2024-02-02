package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestAdapter {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public OneWaySearchRequest from(SearchRequestDto searchRequestDTO){

        return new OneWaySearchRequest(searchRequestDTO.departure(),
                searchRequestDTO.arrival(),
                LocalDate.parse(searchRequestDTO.departureDate(), formatter)
        );

    }

}
