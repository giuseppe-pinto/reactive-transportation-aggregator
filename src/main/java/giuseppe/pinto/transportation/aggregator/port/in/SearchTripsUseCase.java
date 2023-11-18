package giuseppe.pinto.transportation.aggregator.port.in;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDTO;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.TripDTO;
import reactor.core.publisher.Flux;

public interface SearchTripsUseCase {

    Flux<TripDTO> searchOn(SearchRequestDTO searchRequestDTO);

}
