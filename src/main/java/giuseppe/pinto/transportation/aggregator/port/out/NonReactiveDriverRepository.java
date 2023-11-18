package giuseppe.pinto.transportation.aggregator.port.out;

import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import reactor.core.publisher.Mono;

import java.util.List;

public interface NonReactiveDriverRepository {

    Mono<List<Trip>> performRequest(SearchRequest searchRequest);

}
