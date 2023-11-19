package giuseppe.pinto.transportation.aggregator.port.in;

import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.SearchRequestDTO;
import giuseppe.pinto.transportation.aggregator.adapter.primary.rest.dto.TripDTO;
import reactor.core.publisher.Flux;

import java.util.List;

public interface SearchTripsUseCase {

    Flux<TripDTO> searchOnNewVersion(SearchRequestDTO searchRequestDTO);

    Flux<List<TripDTO>> searchOn(SearchRequestDTO searchRequestDTO);

}
