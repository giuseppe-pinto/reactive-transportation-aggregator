package giuseppe.pinto.transportation.aggregator.adapter.secondary.out;

import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.blocking.BlueBlockingDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.blocking.GreenBlockingDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.blocking.RedBlockingDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.reactive.BlueReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.adapter.secondary.out.driver.reactive.GreenReactiveDriverRepository;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.port.out.DriverConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.out.driver.DriverRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public class SimpleDriversConfigurationRepository implements DriverConfigurationRepository {

    private final boolean useReactive;

    public SimpleDriversConfigurationRepository(boolean useReactive) {
        this.useReactive = useReactive;
    }

    @Override
    public Flux<DriverRepository> getDriversFor(OneWaySearchRequest oneWaySearchRequest) {
        if(useReactive){
            return Flux.just(new GreenReactiveDriverRepository(),
                    new BlueReactiveDriverRepository());
        }
        return Flux.just(new BlueBlockingDriverRepository(),
                new RedBlockingDriverRepository(),
                new GreenBlockingDriverRepository());
    }
}
