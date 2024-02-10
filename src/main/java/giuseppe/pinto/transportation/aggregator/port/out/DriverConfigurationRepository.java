package giuseppe.pinto.transportation.aggregator.port.out;

import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.port.out.driver.DriverRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface DriverConfigurationRepository {
    Flux<DriverRepository> getDriversFor(OneWaySearchRequest oneWaySearchRequest);
}
