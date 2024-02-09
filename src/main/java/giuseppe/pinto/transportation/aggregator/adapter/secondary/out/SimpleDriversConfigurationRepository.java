package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.blocking.BlueBlockingDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.blocking.GreenBlockingDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.blocking.RedBlockingDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.reactive.BlueReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.reactive.GreenReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.port.out.DriverConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.out.driver.DriverRepository;

import java.util.List;

public class SimpleDriversConfigurationRepository implements DriverConfigurationRepository {

    private static final List<DriverRepository> reactiveDriverRepository =
            List.of(
                    new GreenReactiveDriverRepository(),
                    new BlueReactiveDriverRepository());
    private static final List<DriverRepository> blockingDriverRepository =
            List.of(new BlueBlockingDriverRepository(),
                    new RedBlockingDriverRepository(),
                    new GreenBlockingDriverRepository());
    private final boolean useReactive;

    public SimpleDriversConfigurationRepository(boolean useReactive) {
        this.useReactive = useReactive;
    }

    @Override
    public List<DriverRepository> getDriversFor(OneWaySearchRequest oneWaySearchRequest) {
        if(useReactive){
            return reactiveDriverRepository;
        }
        return blockingDriverRepository;
    }
}
