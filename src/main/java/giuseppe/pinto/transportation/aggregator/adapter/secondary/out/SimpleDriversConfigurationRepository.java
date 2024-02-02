package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.blocking.BlueBlockingDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.blocking.GreenBlockingDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.blocking.RedBlockingDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.reactive.BlueReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.reactive.GreenReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.port.out.DriverConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.out.BlockingDriverRepository;
import giuseppe.pinto.transportation.aggregator.port.out.ReactiveDriverRepository;

import java.util.List;

public class SimpleDriversConfigurationRepository implements DriverConfigurationRepository {


    @Override
    public List<BlockingDriverRepository> getDriversFor(OneWaySearchRequest oneWaySearchRequest) {

        return List.of(
                new BlueBlockingDriverRepository(),
                new RedBlockingDriverRepository(),
                new GreenBlockingDriverRepository());

    }

    @Override
    public List<ReactiveDriverRepository> getReactiveDriversFor(OneWaySearchRequest oneWaySearchRequest) {
        return List.of(new GreenReactiveDriverRepository(), new BlueReactiveDriverRepository());
    }
}
