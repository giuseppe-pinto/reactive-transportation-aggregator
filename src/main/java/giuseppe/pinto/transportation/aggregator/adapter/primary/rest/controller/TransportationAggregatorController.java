package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDTO;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.TripDTO;
import giuseppe.pinto.transportation.aggregator.port.in.SearchTripsUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.in.StandardSearchTripsUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping(value = "/aggregator")
public class TransportationAggregatorController {

    private final SearchTripsUseCase searchTripsUseCase = new StandardSearchTripsUseCase();

    @PostMapping("/search")
    public Flux<List<TripDTO>> search(@RequestBody SearchRequestDTO searchRequest) {
        return searchTripsUseCase.searchOn(searchRequest);
    }

    @GetMapping("/search")
    public Flux<List<TripDTO>> searchWithGet() {

        SearchRequestDTO searchRequest = SearchRequestDTO.builder()
                .departure("MIL")
                .arrival("NAP")
                .departureDate("2024-01-04")
                .returnDate("2024-01-10")
                .build();

        return searchTripsUseCase.searchOn(searchRequest);
    }


}