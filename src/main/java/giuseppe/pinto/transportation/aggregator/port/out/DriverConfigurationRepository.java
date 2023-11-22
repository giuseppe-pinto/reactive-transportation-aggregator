package giuseppe.pinto.transportation.aggregator.port.out;

import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;

import java.util.List;

public interface DriverConfigurationRepository {
    List<DriverRepository> getDriversFor(SearchRequest searchRequest);
}
