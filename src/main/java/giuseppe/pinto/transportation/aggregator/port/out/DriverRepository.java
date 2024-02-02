package giuseppe.pinto.transportation.aggregator.port.out;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import reactor.core.publisher.Mono;

public interface DriverRepository {

    Mono<DriverOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest);

}
