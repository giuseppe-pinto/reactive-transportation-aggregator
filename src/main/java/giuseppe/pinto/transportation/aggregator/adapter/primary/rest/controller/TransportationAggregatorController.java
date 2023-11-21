package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDto;
import giuseppe.pinto.transportation.aggregator.port.in.SearchTripsUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.in.StandardSearchTripsUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.Solutions;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/aggregator")
public class TransportationAggregatorController {

    private final SearchTripsUseCase searchTripsUseCase = new StandardSearchTripsUseCase();

    @PostMapping("/search")
    public Flux<Solutions> search(@RequestBody SearchRequestDto searchRequest) {
        return searchTripsUseCase.searchOn(searchRequest);
    }

    @GetMapping("/search")
    public Flux<Solutions> searchWithGet() {

        SearchRequestDto searchRequest = SearchRequestDto.builder()
                .departure("MIL")
                .arrival("NAP")
                .departureDate("2024-01-04")
                .returnDate("2024-01-10")
                .build();

        return searchTripsUseCase.searchOn(searchRequest);
    }


}