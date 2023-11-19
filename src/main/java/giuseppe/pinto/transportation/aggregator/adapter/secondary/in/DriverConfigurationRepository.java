package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.port.out.NonReactiveDriverRepository;

import java.util.List;

public interface DriverConfigurationRepository {
    List<NonReactiveDriverRepository> getDriversFor(SearchRequest searchRequest);
}
