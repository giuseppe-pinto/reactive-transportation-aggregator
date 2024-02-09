package giuseppe.pinto.transportation.aggregator.port.out.driver;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import org.reactivestreams.Publisher;

public interface DriverRepository {
    Publisher<DriverOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest);

}
