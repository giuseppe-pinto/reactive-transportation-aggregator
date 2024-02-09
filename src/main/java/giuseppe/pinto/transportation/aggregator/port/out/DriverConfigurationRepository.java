package giuseppe.pinto.transportation.aggregator.port.out;

import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;

import java.util.List;

public interface DriverConfigurationRepository {
    List<DriverRepository> getDriversFor(OneWaySearchRequest oneWaySearchRequest);
}
