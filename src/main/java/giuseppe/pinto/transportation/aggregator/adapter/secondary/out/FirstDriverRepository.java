package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.port.out.DriverRepository;
import reactor.core.publisher.Flux;
import giuseppe.pinto.transportation.aggregator.domain.Trip;

public class FirstDriverRepository implements DriverRepository {
    @Override
    public Flux<Trip> performRequest(SearchRequest searchRequest) {
        return null;
    }
}
