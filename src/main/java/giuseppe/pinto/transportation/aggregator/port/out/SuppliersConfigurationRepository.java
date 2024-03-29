package giuseppe.pinto.transportation.aggregator.port.out;

import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.port.out.supplier.SupplierRepository;
import reactor.core.publisher.Flux;

public interface SuppliersConfigurationRepository {
    Flux<SupplierRepository> getSuppliersFor(OneWaySearchRequest oneWaySearchRequest);
}
