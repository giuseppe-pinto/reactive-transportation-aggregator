package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDto;
import giuseppe.pinto.transportation.aggregator.port.in.SearchUseCase;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.Solutions;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/aggregator")
public class TransportationAggregatorController {

    private final SearchUseCase searchUseCase;

    public TransportationAggregatorController(SearchUseCase searchUseCase) {
        this.searchUseCase = searchUseCase;
    }

    @PostMapping("/search")
    public Flux<Solutions> search(@RequestBody SearchRequestDto searchRequest) {
        return searchUseCase.searchOn(searchRequest);
    }

    @GetMapping("/search")
    public Flux<Solutions> searchWithGet() {

        SearchRequestDto searchRequest = new SearchRequestDto("MXP",
                "NAP",
                "2024-11-12",
                "2024-11-13");

        return searchUseCase.searchOn(searchRequest);
    }


}