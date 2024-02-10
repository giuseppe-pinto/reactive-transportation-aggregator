package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.port.in.DriverOutcomeService;
import giuseppe.pinto.transportation.aggregator.port.out.DriverConfigurationRepository;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class StandardDriverOutcomeService implements DriverOutcomeService {

    private final DriverConfigurationRepository driverConfigurationRepository;

    public StandardDriverOutcomeService(DriverConfigurationRepository driverConfigurationRepository) {
        this.driverConfigurationRepository = driverConfigurationRepository;
    }

    @Override
    public Flux<DriverOutcome> from(OneWaySearchRequest oneWaySearchRequest) {

        return driverConfigurationRepository
                .getDriversFor(oneWaySearchRequest)
                .parallel()
                .runOn(Schedulers.parallel())
                .flatMap(driverRepository -> driverRepository.performRequest(oneWaySearchRequest))
                .sequential();

    }


}
