package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.FirstNonReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.SecondNonReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.ThirdNonReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.port.out.NonReactiveDriverRepository;

import java.util.List;

public class RealDriverConfigurationRepository implements DriverConfigurationRepository {


    @Override
    public List<NonReactiveDriverRepository> getDriversFor(SearchRequest searchRequest) {

        return List.of(
                new FirstNonReactiveDriverRepository(),
                new SecondNonReactiveDriverRepository(),
                new ThirdNonReactiveDriverRepository());

    }
}
