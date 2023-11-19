package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.BlueDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.RedDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.GreenDriverRepository;
import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.port.out.DriverConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.out.MultiTripReactiveDriverRepository;

import java.util.List;

public class RealDriverConfigurationRepository implements DriverConfigurationRepository {


    @Override
    public List<MultiTripReactiveDriverRepository> getDriversFor(SearchRequest searchRequest) {

        return List.of(
                new BlueDriverRepository(),
                new RedDriverRepository(),
                new GreenDriverRepository());

    }
}
