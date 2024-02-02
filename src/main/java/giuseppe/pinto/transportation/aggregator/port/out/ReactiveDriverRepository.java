package giuseppe.pinto.transportation.aggregator.port.out;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import reactor.core.publisher.Flux;

public interface ReactiveDriverRepository {

    Flux<DriverOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest);

}
