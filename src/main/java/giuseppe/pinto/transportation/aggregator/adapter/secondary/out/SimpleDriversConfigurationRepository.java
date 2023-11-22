package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.port.out.DriverConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.out.DriverRepository;

import java.util.List;

public class SimpleDriversConfigurationRepository implements DriverConfigurationRepository {


    @Override
    public List<DriverRepository> getDriversFor(SearchRequest searchRequest) {

        return List.of(
                new BlueDriverRepository(),
                new RedDriverRepository(),
                new GreenDriverRepository());

    }
}
