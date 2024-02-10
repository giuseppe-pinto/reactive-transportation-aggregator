package giuseppe.pinto.transportation.aggregator.port.in;

import giuseppe.pinto.transportation.aggregator.domain.SupplierOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import reactor.core.publisher.Flux;

public interface SuppliersOutcomeService {

    Flux<SupplierOutcome> from(OneWaySearchRequest oneWaySearchRequest);

}

