package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto;

import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestAdapter {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");

    public SearchRequest from(SearchRequestDTO searchRequestDTO){

        return SearchRequest.builder()
                .departure(searchRequestDTO.getDeparture())
                .arrival(searchRequestDTO.getArrival())
                .departureDate(LocalDate.parse(searchRequestDTO.getDepartureDate(), formatter))
                .returnDate(LocalDate.parse(searchRequestDTO.getReturnDate(), formatter))
                .build();
    }

}
