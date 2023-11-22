package giuseppe.pinto.transportation.aggregator.adapter.secondary.in;

import giuseppe.pinto.transportation.aggregator.domain.DriverOutcome;
import giuseppe.pinto.transportation.aggregator.domain.SearchRequest;
import giuseppe.pinto.transportation.aggregator.port.in.TripsRepository;
import giuseppe.pinto.transportation.aggregator.port.out.DriverConfigurationRepository;
import giuseppe.pinto.transportation.aggregator.port.out.DriverRepository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

public class StandardTripsRepository implements TripsRepository {

    private final DriverConfigurationRepository driverConfigurationRepository;

    public StandardTripsRepository(DriverConfigurationRepository driverConfigurationRepository) {
        this.driverConfigurationRepository = driverConfigurationRepository;
    }

    @Override
    public Flux<DriverOutcome> getDriverOutcomeFrom(SearchRequest searchRequest) {

        List<DriverRepository> drivers = driverConfigurationRepository.getDriversFor(searchRequest);

        return Flux.merge(
                drivers
                        .stream()
                        .map(driver -> driver.performRequest(searchRequest))
                        .collect(Collectors.toList()));
    }


}
