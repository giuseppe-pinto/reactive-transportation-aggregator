package giuseppe.pinto.transportation.aggregator.port.out;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import reactor.core.publisher.Mono;

public interface MultiTripReactiveDriverRepository {

    Mono<DriverOutcome> performRequest(SearchRequest searchRequest);

}
