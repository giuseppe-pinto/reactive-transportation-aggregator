package giuseppe.pinto.transportation.aggregator.port.out.supplier;

import giuseppe.pinto.transportation.aggregator.domain.SupplierOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import org.reactivestreams.Publisher;

public interface SupplierRepository {
    Publisher<SupplierOutcome> performRequest(OneWaySearchRequest oneWaySearchRequest);

}
