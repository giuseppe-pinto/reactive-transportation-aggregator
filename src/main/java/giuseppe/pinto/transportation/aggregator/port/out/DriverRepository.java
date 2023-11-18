package giuseppe.pinto.transportation.aggregator.port.out;

import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import reactor.core.publisher.Flux;

public interface DriverRepository {

    Flux<Trip> performRequest(SearchRequest searchRequest);

}
