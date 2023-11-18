package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import giuseppe.pinto.transportation.aggregator.port.out.DriverRepository;
import reactor.core.publisher.Flux;

public class ThirdDriverRepository implements DriverRepository {
    @Override
    public Flux<Trip> performRequest(SearchRequest searchRequest) {
        return null;
    }
}
