package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.port.in.DriverOutcomeService;
import giuseppe.pinto.transportation.aggregator.port.out.*;
import giuseppe.pinto.transportation.aggregator.port.out.driver.DriverRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.List;
import java.util.stream.Collectors;

public class StandardDriverOutcomeService implements DriverOutcomeService {

    private final DriverConfigurationRepository driverConfigurationRepository;

    public StandardDriverOutcomeService(DriverConfigurationRepository driverConfigurationRepository) {
        this.driverConfigurationRepository = driverConfigurationRepository;
    }

    @Override
    public Flux<DriverOutcome> from(OneWaySearchRequest oneWaySearchRequest) {

        Flux<DriverRepository> drivers = driverConfigurationRepository
                .getDriversFor(oneWaySearchRequest);

        return Flux.merge(drivers.map(driverRepository -> driverRepository.performRequest(oneWaySearchRequest)));


        /*return Flux.merge( drivers
                    .stream()
                    .map(driver -> driver.performRequest(oneWaySearchRequest))
                    .collect(Collectors.toList()));*/

    }


}
