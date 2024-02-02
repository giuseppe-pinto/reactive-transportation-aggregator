package giuseppe.pinto.transportation.aggregator.adapter.primary.rest.controller;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.OneWaySearchRequestDto;
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
    public Flux<Solutions> search(@RequestBody OneWaySearchRequestDto oneWaySearchRequestDto) {
        return searchUseCase.searchOn(oneWaySearchRequestDto);
    }

    @GetMapping("/search")
    public Flux<Solutions> searchWithGet() {

        OneWaySearchRequestDto oneWaySearchRequestDto = new OneWaySearchRequestDto("MXP",
                "NAP",
                "2024-11-12"
        );

        return searchUseCase.searchOn(oneWaySearchRequestDto);
    }


}