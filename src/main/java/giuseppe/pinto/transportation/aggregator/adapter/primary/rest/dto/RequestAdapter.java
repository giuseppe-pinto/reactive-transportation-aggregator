package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestAdapter {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SearchRequest from(SearchRequestDto searchRequestDTO){

        return new SearchRequest(searchRequestDTO.departure(),
                searchRequestDTO.arrival(),
                LocalDate.parse(searchRequestDTO.departureDate(), formatter),
                LocalDate.parse(searchRequestDTO.returnDate(), formatter));

    }

}
