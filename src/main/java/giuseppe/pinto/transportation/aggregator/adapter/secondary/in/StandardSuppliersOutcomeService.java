package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.domain.SupplierOutcome;
import giuseppe.pinto.transportation.aggregator.domain.OneWaySearchRequest;
import giuseppe.pinto.transportation.aggregator.port.in.SuppliersOutcomeService;
import giuseppe.pinto.transportation.aggregator.port.out.SuppliersConfigurationRepository;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class StandardSuppliersOutcomeService implements SuppliersOutcomeService {

    private final SuppliersConfigurationRepository suppliersConfigurationRepository;

    public StandardSuppliersOutcomeService(SuppliersConfigurationRepository suppliersConfigurationRepository) {
        this.suppliersConfigurationRepository = suppliersConfigurationRepository;
    }

    @Override
    public Flux<SupplierOutcome> from(OneWaySearchRequest oneWaySearchRequest) {

        return suppliersConfigurationRepository
                .getSuppliersFor(oneWaySearchRequest)
                .parallel()
                .runOn(Schedulers.parallel())
                .flatMap(driverRepository -> driverRepository.performRequest(oneWaySearchRequest))
                .sequential();

    }


}
