package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.port.in.DriverOutcomeRepository;
import giuseppe.pinto.transportation.aggregator.port.out.DriverConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.out.BlockingDriverRepository;
import giuseppe.pinto.transportation.aggregator.port.out.ReactiveDriverRepository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

public class StandardDriverOutcomeRepository implements DriverOutcomeRepository {

    private final DriverConfigurationRepository driverConfigurationRepository;
    private final boolean useReactiveDrivers;

    public StandardDriverOutcomeRepository(DriverConfigurationRepository driverConfigurationRepository, boolean useReactiveDrivers) {
        this.driverConfigurationRepository = driverConfigurationRepository;
        this.useReactiveDrivers = useReactiveDrivers;
    }

    @Override
    public Flux<DriverOutcome> from(OneWaySearchRequest oneWaySearchRequest) {

        if(useReactiveDrivers){
            List<ReactiveDriverRepository> drivers = driverConfigurationRepository.getReactiveDriversFor(oneWaySearchRequest);

            return Flux.merge(
                    drivers
                            .stream()
                            .map(driver -> driver.performRequest(oneWaySearchRequest))
                            .collect(Collectors.toList()));
        } else {

            List<BlockingDriverRepository> drivers = driverConfigurationRepository.getDriversFor(oneWaySearchRequest);

            return Flux.merge(
                    drivers
                            .stream()
                            .map(driver -> driver.performRequest(oneWaySearchRequest))
                            .collect(Collectors.toList()));
        }

    }


}
