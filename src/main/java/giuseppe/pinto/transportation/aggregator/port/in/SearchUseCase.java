package giuseppe.pinto.transportation.aggregator.port.in;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.OneWaySearchRequestDto;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.Solutions;
import reactor.core.publisher.Flux;

public interface SearchUseCase {

    Flux<Solutions> searchOn(OneWaySearchRequestDto oneWaySearchRequestDTO);

}
