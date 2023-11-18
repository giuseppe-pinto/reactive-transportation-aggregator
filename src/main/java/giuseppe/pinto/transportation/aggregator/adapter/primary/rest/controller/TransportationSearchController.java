package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDTO;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.TripDTO;
import giuseppe.pinto.transportation.aggregator.port.in.SearchTripsUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.in.StandardSearchTripsUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;

public class TransportationSearchController {

    SearchTripsUseCase searchTripsUseCase = new StandardSearchTripsUseCase();

    @PostMapping("/search")
    public Flux<TripDTO> getEmployeeById(@RequestBody SearchRequestDTO searchRequest) {
        return searchTripsUseCase.searchOn(searchRequest);
    }



}
