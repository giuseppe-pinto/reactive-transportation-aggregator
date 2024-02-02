package giuseppe.pinto.transportation.aggregator.adapter.secondary.out.mono;

import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.port.out.DriverConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.out.BlockingDriverRepository;

import java.util.List;

public class SimpleDriversConfigurationRepository implements DriverConfigurationRepository {


    @Override
    public List<BlockingDriverRepository> getDriversFor(OneWaySearchRequest oneWaySearchRequest) {

        return List.of(
                new BlueBlockingDriverRepository(),
                new RedBlockingDriverRepository(),
                new GreenBlockingDriverRepository());

    }
}
