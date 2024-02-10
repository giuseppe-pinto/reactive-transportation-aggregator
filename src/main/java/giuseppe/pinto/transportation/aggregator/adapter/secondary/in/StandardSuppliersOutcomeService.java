package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.domain.SupplierOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.port.in.SuppliersOutcomeService;
import giuseppe.pinto.transportation.aggregator.port.out.DriverConfigurationRepository;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class StandardSuppliersOutcomeService implements SuppliersOutcomeService {

    private final DriverConfigurationRepository driverConfigurationRepository;

    public StandardSuppliersOutcomeService(DriverConfigurationRepository driverConfigurationRepository) {
        this.driverConfigurationRepository = driverConfigurationRepository;
    }

    @Override
    public Flux<SupplierOutcome> from(OneWaySearchRequest oneWaySearchRequest) {

        return driverConfigurationRepository
                .getDriversFor(oneWaySearchRequest)
                .parallel()
                .runOn(Schedulers.parallel())
                .flatMap(driverRepository -> driverRepository.performRequest(oneWaySearchRequest))
                .sequential();

    }


}
