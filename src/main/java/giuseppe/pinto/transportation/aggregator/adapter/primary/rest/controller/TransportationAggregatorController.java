package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDTO;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.TripDTO;
import giuseppe.pinto.transportation.aggregator.port.in.SearchTripsUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.in.StandardSearchTripsUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping(value = "/aggregator")
public class TransportationAggregatorController {

    private final SearchTripsUseCase searchTripsUseCase = new StandardSearchTripsUseCase();

    @PostMapping("/search")
    public Flux<List<TripDTO>> getTrips(@RequestBody SearchRequestDTO searchRequest) {
        return searchTripsUseCase.searchOn(searchRequest);
    }


    @GetMapping("/health")
    public String health() {
        return "OK";
    }



}
