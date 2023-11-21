package giuseppe.pinto.transportation.aggregator.port.in;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.domain.Trip;
import reactor.core.publisher.Flux;

import java.util.List;

public interface TripsRepository {

    Flux<DriverOutcome> getDriverOutcomeFrom(SearchRequest searchRequest);

}

