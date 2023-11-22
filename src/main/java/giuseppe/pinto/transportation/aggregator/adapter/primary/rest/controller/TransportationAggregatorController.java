package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDto;
import giuseppe.pinto.transportation.aggregator.port.in.SearchTripsUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.Solutions;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/aggregator")
public class TransportationAggregatorController {

    private final SearchTripsUseCase searchTripsUseCase;

    public TransportationAggregatorController(SearchTripsUseCase searchTripsUseCase) {
        this.searchTripsUseCase = searchTripsUseCase;
    }

    @PostMapping("/search")
    public Flux<Solutions> search(@RequestBody SearchRequestDto searchRequest) {
        return searchTripsUseCase.searchOn(searchRequest);
    }

    @GetMapping("/search")
    public Flux<Solutions> searchWithGet() {

        SearchRequestDto searchRequest = SearchRequestDto.builder()
                .departure("MXP")
                .arrival("NAP")
                .departureDate("2024-11-12")
                .returnDate("2024-11-13")
                .build();

        return searchTripsUseCase.searchOn(searchRequest);
    }


}